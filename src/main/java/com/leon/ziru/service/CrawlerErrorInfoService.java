package com.leon.ziru.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leon.ziru.dao.CrawlerErrorInfoDao;
import com.leon.ziru.dao.UserDao;
import com.leon.ziru.exception.BusinessError;
import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.model.AccessTokenResp;
import com.leon.ziru.model.send_temp.SendTemplateReq;
import com.leon.ziru.model.ziru.tables.pojos.CrawlerErrorInfo;
import com.leon.ziru.model.ziru.tables.pojos.Mission;
import com.leon.ziru.model.ziru.tables.pojos.User;
import com.leon.ziru.service.model.CrawlerErrorInfoItem;
import com.leon.ziru.util.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.leon.ziru.service.MissionService.*;

/**
 * Created by lituancheng on 2018/10/25
 */
@Service
public class CrawlerErrorInfoService {

    @Autowired
    private CrawlerErrorInfoDao crawlerErrorInfoDao;

    @Autowired
    private MissionService missionService;

    @Autowired
    private UserDao userDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    /**
     * 新增爬取失败信息
     */
    public void addErrInfo(int missionId){
        CrawlerErrorInfo errorInfo = crawlerErrorInfoDao.getByMissionId(missionId);
        if(errorInfo == null){
            crawlerErrorInfoDao.createErrorInfo(missionId);
        }else {
            crawlerErrorInfoDao.addCount(missionId, errorInfo.getErrCount() + 1);
        }
    }

    public List<CrawlerErrorInfoItem> list(Integer status){
        return crawlerErrorInfoDao.list(status);
    }

    public boolean notice(int missionId){
        Mission mission = missionService.get(missionId);
        AccessTokenResp accessTokenResp;
        try {
            accessTokenResp = HttpClientUtil.httpGet(GET_ACCESS_TOKEN_URL, AccessTokenResp.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessError.GENENRAL, "获取accessToken失败");
        }
        //服务模板
        if(accessTokenResp.errcode == 0) {
            String sendUrl = SEND_TEMPLATE_URL + accessTokenResp.access_token;
            if(StringUtils.isNotEmpty(mission.getFormId())) {
                SendTemplateReq req = new SendTemplateReq();
                User user = userDao.get(mission.getUserId());
                req.touser = user.getOpenId();
                req.template_id = SEND_TEMPLATE_ID;
                req.page = "pages/mission/mission?missionId=" + missionId;
                req.form_id = mission.getFormId();
                SendTemplateReq.Data data = new SendTemplateReq.Data();
                data.keyword1 = new SendTemplateReq.Data.Keyword("您监控的房源状态异常，请及时查看");
                data.keyword2 = new SendTemplateReq.Data.Keyword(sdf.format(new Date()));
                data.keyword3 = new SendTemplateReq.Data.Keyword("如房源消失，请修改任务");
                data.keyword4 = new SendTemplateReq.Data.Keyword(mission.getRoomName());
                req.data = data;
                String resp = HttpClientUtil.httpPostJson(sendUrl, gson.toJson(req));
                if(StringUtils.isNotEmpty(resp))
                    return crawlerErrorInfoDao.haveNotice(missionId);
            }else {
                throw new BusinessException(BusinessError.GENENRAL, "该任务无formId");
            }
        }
        return false;
    }
}
