package com.leon.ziru.controller.api;

import com.leon.ziru.model.Respond;
import com.leon.ziru.model.help.HelpMissionInfo;
import com.leon.ziru.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/help")
public class HelpController {

    @Autowired
    private HelpService helpService;

    @RequestMapping("add")
    public Respond add(@RequestParam(value = "missionId") Integer missionId,
                       @RequestParam(value = "packageNum") Integer packageNum,
                       @RequestParam(value = "openid") String openId){
        HelpMissionInfo helpMissionInfo = helpService.add(missionId, packageNum, openId);
        return new Respond(helpMissionInfo);
    }

    @RequestMapping("get")
    public Respond missionHelpInfo(@RequestParam(value = "missionId") Integer missionId,
                                   @RequestParam(value = "openid") String openId){
        HelpMissionInfo helpMissionInfo = helpService.helpMissionInfo(missionId, openId);
        return new Respond(helpMissionInfo);
    }
}
