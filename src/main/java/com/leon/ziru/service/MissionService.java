package com.leon.ziru.service;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.leon.ziru.dao.MissionDao;
import com.leon.ziru.dao.UserDao;
import com.leon.ziru.exception.BusinessError;
import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.log.ZRLogger;
import com.leon.ziru.mail.Mailer;
import com.leon.ziru.model.AccessTokenResp;
import com.leon.ziru.model.RoomDetailResp;
import com.leon.ziru.model.RoomDetailResp.RoomDetailData;
import com.leon.ziru.model.consts.CityCode;
import com.leon.ziru.model.send_temp.SendTemplateReq;
import com.leon.ziru.model.session.SessionUser;
import com.leon.ziru.model.ziru.tables.pojos.Mission;
import com.leon.ziru.model.ziru.tables.pojos.User;
import com.leon.ziru.util.HttpClientUtil;
import com.leon.ziru.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.leon.ziru.model.consts.WxKeys.APPID;
import static com.leon.ziru.model.consts.WxKeys.SECRET;

@Service
public class MissionService {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;

    private static final String EMAIL_PATTERN = "^([\\w-_]+(?:\\.[\\w-_]+)*)@((?:[a-z0-9]+(?:-[a-zA-Z0-9]+)*)+\\.[a-z]{2,6})$";
    private static final String ZR_DETAIL_PATTERN = "https?://m\\.ziroom.com/(.*?)/room\\?id=([0-9]+).*";
    private static final String DETAIL_TEMPLATE = "http://m.ziroom.com/v7/room/detail.json?city_code=%s&id=%s";
    private static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID +"&secret=" + SECRET;
    private static final String SEND_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";

    //dzz 可入住 ycz 已入住 yxd 已預定 zxpzz 配置中 tzpzz 配置中 sfz 倒计时中
    private HashMap<String, Integer> statusMap = new HashMap<String, Integer>(){
        {
            put("dzz", 1);
            put("ycz", 2);
            put("yxd", 3);
            put("zxpzz", 4);
            put("tzpzz", 4);
            put("sfz", 5);
        }
    };

    @Autowired
    private Mailer mailer;

    @Autowired
    private SmsService smsService;

    @Autowired
    private MissionDao missionDao;

    @Autowired
    private UserDao userDao;

    public Mission get(Integer id){
        return missionDao.get(id);
    }

    public List<Mission> list(String token){
        Integer userId = SessionUtil.getUserId(token);
        return missionDao.getList(userId);
    }

    public Mission saveMission(String sourceUrl, String email, String formId, Integer id, String token) throws Exception {
        Integer userId = SessionUtil.getUserId(token);
        if(id == null){ //新增
            List<Mission> enableList = missionDao.getEnableList(userId);
            if(enableList != null && enableList.size() > 1)
                throw new BusinessException(BusinessError.GENENRAL, "因服务器资源有限,现阶段每人只能添加一个监控任务");
        }
        if(!Pattern.matches(EMAIL_PATTERN, email))
            throw new BusinessException(BusinessError.GENENRAL, "邮箱格式不正确");
        RoomDetailData detail = getDetail(sourceUrl);
        if(detail.status.equals("zxpzz") || detail.status.equals("tzpzz")){
        }else {
            throw new BusinessException(BusinessError.GENENRAL, "只有配置中和空气质量指数检测中的房源可以监控");
        }
        Mission m = missionDao.get(sourceUrl, userId);
        if(m != null)
            throw new BusinessException(BusinessError.GENENRAL, "该任务已存在，请不要重复创建");
        Mission mission = new Mission();
        mission.setRoomName(detail.name);
        mission.setRoomStatus(statusMap.get(detail.status));
        mission.setSourceUrl(sourceUrl);
        mission.setUserId(userId);
        mission.setEmail(email);
        mission.setFormId(formId);
        if(id == null){
            missionDao.insert(mission);
        }else {
            mission.setId(id);
            missionDao.update(mission);
        }
        return mission;
    }

    public RoomDetailData getDetail(String url) throws Exception {
        Pattern compile = Pattern.compile(ZR_DETAIL_PATTERN);
        Matcher matcher = compile.matcher(url);
        if(matcher.find()){
            String code = matcher.group(1);
            CityCode cityCode = CityCode.codeOf(code);
            if(cityCode == null)
                throw new BusinessException(BusinessError.GENENRAL, "目前不支持监控该区域的房源，如有需要请联系客服");
            String roomId = matcher.group(2);
            Map<String, String> headers = Maps.newHashMap();
            headers.put("Accept", "application/json;version=");
            headers.put("Referer", url);
            headers.put("Host", "m.ziroom.com");
            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
            String content = HttpClientUtil.httpGet(String.format(DETAIL_TEMPLATE, cityCode.getCode(), roomId), headers);
            RoomDetailResp resp = gson.fromJson(content, RoomDetailResp.class);
            if(resp.error_code == 0){
                return resp.data;
            }
            else
                throw new BusinessException(BusinessError.ZIRU_GET_DATA_ERROR);
        }else {
            throw new BusinessException(BusinessError.GENENRAL, "链接格式错误");
        }
    }

    public boolean delete(Integer id, String token){
        SessionUser user = SessionUtil.get(token);
        Mission mission = missionDao.get(id);
        if(!mission.getUserId().equals(user.userId))
            throw new BusinessException(BusinessError.ZIRU_ACCESS_DATA_ERROR);
        return missionDao.delete(id);
    }

    /**
     * 每隔5分钟监测一波房源变化
     */
    @Scheduled(cron = "0 0/5 *  * * ? ")
    public void monitoring() {
        AccessTokenResp accessTokenResp = null;
        List<Mission> missionList = missionDao.getAllEnableList();
        for(Mission m : missionList){
            try {
                RoomDetailData detail = getDetail(m.getSourceUrl());
                Integer status = statusMap.get(detail.status);
                if(!status.equals(m.getRoomStatus())){    //房源状态改变了
                    //邮件
                    mailer.sendSimpleMail("自如抢房通知",
                            "您监控的房源【" + m.getRoomName() + "】状态更新了，请及时前往自如App查看", m.getEmail(), m.getId());
                    m.setRoomStatus(status);
                    missionDao.update(m);
                    missionDao.setClose(m.getId());

                    if(accessTokenResp == null)
                        accessTokenResp = HttpClientUtil.httpGet(GET_ACCESS_TOKEN_URL, AccessTokenResp.class);

                    User user = userDao.get(m.getUserId());

                    //服务模板
                    if(accessTokenResp.errcode == 0) {
                        String sendUrl = SEND_TEMPLATE_URL + accessTokenResp.access_token;
                        if(StringUtils.isNotEmpty(m.getFormId())) {
                            SendTemplateReq req = new SendTemplateReq();
                            req.touser = user.getOpenId();
                            req.template_id = "WwDXdcsgyYQ6iF13WsuPHKJ1Uda_GQ7r0amFEuwNuJg";
                            req.page = "pages/dashang/dashang";
                            req.form_id = m.getFormId();
                            SendTemplateReq.Data data = new SendTemplateReq.Data();
                            data.keyword1 = new SendTemplateReq.Data.Keyword("您监控的房源状态有变化啦");
                            data.keyword2 = new SendTemplateReq.Data.Keyword(sdf.format(new Date()));
                            data.keyword3 = new SendTemplateReq.Data.Keyword("打赏一下~");
                            data.keyword4 = new SendTemplateReq.Data.Keyword(m.getRoomName());
                            req.data = data;
                            String resp = HttpClientUtil.httpPostJson(sendUrl, gson.toJson(req));
                            if(StringUtils.isNotEmpty(resp))
                                missionDao.sendTemplateSuccess(m.getId());
                        }
                    }

                    //短信
                    if(StringUtils.isNotEmpty(user.getPhone())){
                        smsService.send(user.getPhone(), m.getId());
                    }
                }
            } catch (JsonSyntaxException e){
                ZRLogger.debugLog.debug("monitoring Exception: " + m.getRoomName() + ":", e);
            } catch (Exception e) {
                ZRLogger.errorLog.error("monitoring Exception: " + m.getRoomName() + ":", e);
            }
        }
    }
}
