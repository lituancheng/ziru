package com.leon.ziru.controller.api;

import com.leon.ziru.model.Respond;
import com.leon.ziru.model.RoomDetailResp;
import com.leon.ziru.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mission")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @RequestMapping("add")
    public Respond add(String sourceUrl, String email, String token) throws Exception {
        RoomDetailResp.RoomDetailData detail = missionService.getDetail(sourceUrl);
        return new Respond(detail);
    }
}
