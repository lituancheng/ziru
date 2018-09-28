package com.leon.ziru.controller.api;

import com.leon.ziru.model.Respond;
import com.leon.ziru.model.ziru.tables.pojos.Mission;
import com.leon.ziru.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mission")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @RequestMapping("add")
    public Respond add(@RequestParam(value = "sourceUrl") String sourceUrl,
                       @RequestParam(value = "email") String email,
                       @RequestParam(value = "token") String token) throws Exception {
        Mission mission = missionService.addMission(sourceUrl, email, token);
        return new Respond(mission);
    }

    @RequestMapping("get")
    public Respond get(@RequestParam(value = "id") Integer id) throws Exception {
        Mission mission = missionService.get(id);
        return new Respond(mission);
    }

    @RequestMapping("list")
    public Respond list(@RequestParam(value = "token") String token) throws Exception {
        List<Mission> missionList = missionService.list(token);
        return new Respond(missionList);
    }
}
