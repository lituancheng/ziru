package com.leon.ziru.service;

import com.leon.ziru.dao.MissionDao;
import com.leon.ziru.log.ZRLogger;
import com.leon.ziru.model.SmsResp;
import com.leon.ziru.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by lituancheng on 2018/10/11
 */
@Service
public class SmsService {

    private static final String SMS_URL = "http://v.juhe.cn/sms/send?mobile=%s&tpl_id=%s&tpl_value=&key=%s";
    private static final String TEMPLATE_ID = "106321";
    private static final String APP_KEY = "79a5793d79bc3d90f590eaef40accaca";

    @Autowired
    private MissionDao missionDao;

    @Async
    public void send(String phone, Integer missionId){
        String url = String.format(SMS_URL, phone, TEMPLATE_ID, APP_KEY);
        try {
            SmsResp smsResp = HttpClientUtil.httpGet(url, SmsResp.class);
            if(smsResp.error_code == 0)
                missionDao.sendSmsSuccess(missionId);
            else
                ZRLogger.errorLog.error("SmsException:" + smsResp.reason);
        } catch (Exception e) {
            ZRLogger.errorLog.error("SmsException:", e);
        }
    }
}
