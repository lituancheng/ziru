package com.leon.ziru.controller.api;

import com.leon.ziru.model.Respond;
import com.leon.ziru.model.ziru.tables.pojos.HomePageMessage;
import com.leon.ziru.service.HomePageMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lituancheng on 2018/12/24
 */
@RestController
@RequestMapping("/home_page_message")
public class HomePageMessageController {

    @Autowired
    private HomePageMessageService homePageMessageService;

    @RequestMapping("get")
    public Respond get(){
        HomePageMessage homePageMessage = homePageMessageService.get();
        return new Respond(homePageMessage);
    }
}
