package com.leon.ziru.service;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leon.ziru.dao.MissionDao;
import com.leon.ziru.exception.BusinessError;
import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.model.RoomDetailResp;
import com.leon.ziru.model.RoomDetailResp.RoomDetailData;
import com.leon.ziru.model.consts.CityCode;
import com.leon.ziru.model.ziru.tables.pojos.Mission;
import com.leon.ziru.util.HttpClientUtil;
import com.leon.ziru.util.SessionUtil;
import org.jooq.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MissionService {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    private static final String ZR_DETAIL_PATTERN = "https?://m\\.ziroom.com/(.*?)/room\\?id=([0-9]+).*";
    private static final String DETAIL_TEMPLATE = "http://m.ziroom.com/v7/room/detail.json?city_code=%s&id=%s";

    //dzz 可入住 ycz 已入住 yxd 已預定 zxpzz 配置中 tzpzz 配置中
    private HashMap<String, Integer> statusMap = new HashMap<String, Integer>(){
        {
            put("dzz", 1);
            put("ycz", 2);
            put("yxd", 3);
            put("zxpzz", 4);
            put("tzpzz", 4);
        }
    };

    @Autowired
    private MissionDao missionDao;

    public Mission get(Integer id){
        return missionDao.get(id);
    }

    public List<Mission> list(String token){
        Integer userId = SessionUtil.getUserId(token);
        return missionDao.getList(userId);
    }

    public Mission addMission(String sourceUrl, String email, String token) throws Exception {
        Integer userId = SessionUtil.getUserId(token);
        List<Mission> enableList = missionDao.getEnableList(userId);
        if(enableList != null && enableList.size() > 1)
            throw new BusinessException(BusinessError.GENENRAL, "因服务器资源有限,现阶段每人只能添加一个监控任务");
        RoomDetailData detail = getDetail(sourceUrl);
        Mission mission = missionDao.get(sourceUrl, userId);
        if(mission != null)
            throw new BusinessException(BusinessError.GENENRAL, "该任务已存在，请不要重复创建");
        Mission newMission = new Mission();
        newMission.setRoomName(detail.name);
        newMission.setBedRoomCount(detail.bedroom);
        newMission.setRoomNo(detail.index_no);
        newMission.setFace(detail.face);
        newMission.setFloor(detail.floor);
        newMission.setFloorTotal(detail.floor_total);
        newMission.setSubwayPrimary(detail.subway_primary);
        newMission.setRoomStatus(statusMap.get(detail.status));
        newMission.setSourceUrl(sourceUrl);
        newMission.setUserId(userId);
        newMission.setEmail(email);
        missionDao.insert(newMission);
        return newMission;
    }

    public RoomDetailData getDetail(String url) throws Exception {
        Pattern compile = Pattern.compile(ZR_DETAIL_PATTERN);
        Matcher matcher = compile.matcher(url);
        if(matcher.find()){
            String code = matcher.group(1);
            CityCode cityCode = CityCode.codeOf(code);
            if(cityCode == null)
                return null;
            String roomId = matcher.group(2);
            Map<String, String> headers = Maps.newHashMap();
            headers.put("Accept", "application/json;version=");
            headers.put("Referer", url);
            headers.put("Host", "m.ziroom.com");
            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
            String content = HttpClientUtil.httpGet(String.format(DETAIL_TEMPLATE, cityCode.getCode(), roomId), headers);
            RoomDetailResp resp = gson.fromJson(content, RoomDetailResp.class);
            if(resp.error_code == 0){
                RoomDetailData data = resp.data;
                if(data.status.equals("zxpzz") || data.status.equals("tzpzz")
                        || (data.status.equals("dzz") && !StringUtils.isBlank(data.will_unrent_date))){
                    return data;
                }else {
                    throw new BusinessException(BusinessError.GENENRAL, "只有配置中和待释放的房源可以监控");
                }
            }
            else
                throw new BusinessException(BusinessError.ZIRU_GET_DATA_ERROR);
        }else {
            throw new BusinessException(BusinessError.GENENRAL, "链接格式错误");
        }
    }
}
