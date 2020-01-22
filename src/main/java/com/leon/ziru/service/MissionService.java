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
import com.leon.ziru.model.consts.SpeedLevel;
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

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String EMAIL_PATTERN = "^([\\w-_]+(?:\\.[\\w-_]+)*)@((?:[a-z0-9]+(?:-[a-zA-Z0-9]+)*)+\\.[a-z]{2,6})$";

    private static final String ZR_DETAIL_PATTERN_1 = "https?://m\\.ziroom.com/(.*?)/room\\?id=([0-9]+).*";
    private static final String ZR_DETAIL_PATTERN_2 = "https?://m\\.ziroom.com/(.*?)/.*?/([0-9]+).*";

    private static final String DETAIL_TEMPLATE = "http://m.ziroom.com/wap/detail/room.json?city_code=%s&id=%s";
    static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID +"&secret=" + SECRET;
    static final String SEND_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";
    static final String SEND_TEMPLATE_ID = "WwDXdcsgyYQ6iF13WsuPHKJ1Uda_GQ7r0amFEuwNuJg";

    //dzz 待释放 ycz 已入住 yxd 已預定 zxpzz 配置中 tzpzz 配置中 sfz 倒计时中
    private HashMap<String, Integer> statusMap = new HashMap<String, Integer>(){
        {
            put("dzz", 1);
            put("ycz", 2);
            put("yxd", 3);
            put("zxpzz", 6);
            put("tzpzz", 4);
            put("sfz", 5);
        }
    };

    @Autowired
    private Mailer mailer;

    @Autowired
    private SmsService smsService;

    @Autowired
    private CrawlerErrorInfoService crawlerErrorInfoService;

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
        if(detail.status.equals("zxpzz") || detail.status.equals("tzpzz") || detail.status.equals("dzz")){
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
            mission.setId(missionDao.insert(mission));
        }else {
            mission.setId(id);
            missionDao.update(mission);
        }
        return mission;
    }

    public static RoomDetailData getDetail(String url) throws Exception {
        Pattern compile1 = Pattern.compile(ZR_DETAIL_PATTERN_1);
        Matcher matcher1 = compile1.matcher(url.trim());

        Pattern compile2 = Pattern.compile(ZR_DETAIL_PATTERN_2);
        Matcher matcher2 = compile2.matcher(url.trim());

        if(matcher1.matches()){
            return parseDetail(url, matcher1);
        }else if(matcher2.matches()){
            return parseDetail(url, matcher2);
        }else {
            throw new BusinessException(BusinessError.GENENRAL, "链接格式错误");
        }
    }

    private static RoomDetailData parseDetail(String url, Matcher matcher) throws Exception {
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
        if(resp != null && resp.error_code == 0){
            return resp.data;
        }
        else
            throw new BusinessException(BusinessError.ZIRU_GET_DATA_ERROR);
    }

    public boolean delete(Integer id, String token){
        SessionUser user = SessionUtil.get(token);
        Mission mission = missionDao.get(id);
        if(!mission.getUserId().equals(user.userId))
            throw new BusinessException(BusinessError.ZIRU_ACCESS_DATA_ERROR);
        return missionDao.delete(id);
    }

    /**
     * VIP
     */
    @Scheduled(cron = "0 0/7 *  * * ? ")
    public void vipMonitoring() {
        List<Mission> missionList = missionDao.getEnableListByLevel(SpeedLevel.VIP.getLevel());
        spiderStart(missionList);
    }

    /**
     * 高速
     */
    @Scheduled(cron = "0 0/17 *  * * ? ")
    public void highMonitoring() {
        List<Mission> missionList = missionDao.getEnableListByLevel(SpeedLevel.HIGH.getLevel());
        spiderStart(missionList);
    }

    /**
     * 快速
     */
    @Scheduled(cron = "0 0/27 *  * * ? ")
    public void quickMonitoring() {
        List<Mission> missionList = missionDao.getEnableListByLevel(SpeedLevel.QUICK.getLevel());
        spiderStart(missionList);
    }

    /**
     * 中速
     */
    @Scheduled(cron = "0 0/37 *  * * ? ")
    public void middleMonitoring() {
        List<Mission> missionList = missionDao.getEnableListByLevel(SpeedLevel.MIDDLE.getLevel());
        spiderStart(missionList);
    }

    /**
     * 低速
     */
    @Scheduled(cron = "0 0/47 *  * * ? ")
    public void lowMonitoring() {
        List<Mission> missionList = missionDao.getEnableListByLevel(SpeedLevel.LOW.getLevel());
        spiderStart(missionList);
    }

    private void spiderStart(List<Mission> missionList){
        AccessTokenResp accessTokenResp = null;
        for(Mission m : missionList){
            try {
                RoomDetailData detail = getDetail(m.getSourceUrl());
                Integer status = statusMap.get(detail.status);
                if(!status.equals(m.getStatus())){    //房源状态改变了
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
                            req.template_id = SEND_TEMPLATE_ID;
                            req.page = "pages/dashang/dashang";
                            req.form_id = m.getFormId();
                            SendTemplateReq.Data data = new SendTemplateReq.Data();
                            data.keyword1 = new SendTemplateReq.Data.Keyword("您监控的房源状态有变化啦");
                            data.keyword2 = new SendTemplateReq.Data.Keyword(sdf.format(new Date()));
                            data.keyword3 = new SendTemplateReq.Data.Keyword("帮到您了？打赏一下");
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
            } catch (JsonSyntaxException | IllegalStateException e){
                ZRLogger.debugLog.debug("monitoring Exception: " + m.getRoomName() + ":", e);
                crawlerErrorInfoService.addErrInfo(m.getId());
            } catch (Exception e) {
                ZRLogger.errorLog.error("monitoring Exception: " + m.getRoomName() + ":", e);
            }
        }
    }
}
