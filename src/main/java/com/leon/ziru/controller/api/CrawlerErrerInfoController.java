package com.leon.ziru.controller.api;

import com.leon.ziru.exception.BusinessError;
import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.model.Respond;
import com.leon.ziru.service.CrawlerErrorInfoService;
import com.leon.ziru.service.model.CrawlerErrorInfoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lituancheng on 2018/10/25
 */
@RestController
@RequestMapping("/crawler_error_info")
public class CrawlerErrerInfoController {

    @Autowired
    private CrawlerErrorInfoService crawlerErrorInfoService;

    private static final String MY_TOKEN = "grImyvTL3SYbAOUlGNsIMAIy6SljgTwUNNP2";

    @RequestMapping("list")
    public Respond crawlerErrorInfoList(@RequestParam(required = false) Integer status, @RequestParam String token){
        if(!MY_TOKEN.equals(token))
            throw new BusinessException(BusinessError.GENENRAL, "token不正确");
        List<CrawlerErrorInfoItem> list = crawlerErrorInfoService.list(status);
        return new Respond(list);
    }

    @RequestMapping("notice")
    public Respond crawlerErrorInfoList(@RequestParam int missionId, @RequestParam String token){
        if(!MY_TOKEN.equals(token))
            throw new BusinessException(BusinessError.GENENRAL, "token不正确");
        boolean result = crawlerErrorInfoService.notice(missionId);
        return new Respond(result);
    }
}
