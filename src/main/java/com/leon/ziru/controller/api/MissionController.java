package com.leon.ziru.controller.api;

import com.leon.ziru.exception.BusinessError;
import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.model.Respond;
import com.leon.ziru.model.ziru.tables.pojos.Mission;
import com.leon.ziru.service.MissionService;
import org.apache.commons.lang3.StringUtils;
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
        if(StringUtils.isEmpty(sourceUrl) || StringUtils.isEmpty(email))
            throw new BusinessException(BusinessError.GENENRAL, "请填写完毕再提交任务");
        Mission mission = missionService.saveMission(sourceUrl, email, null, token);
        return new Respond(mission);
    }

    @RequestMapping("update")
    public Respond add(@RequestParam(value = "sourceUrl") String sourceUrl,
                       @RequestParam(value = "email") String email,
                       @RequestParam(value = "id") Integer id,
                       @RequestParam(value = "token") String token) throws Exception {
        if(StringUtils.isEmpty(sourceUrl) || StringUtils.isEmpty(email))
            throw new BusinessException(BusinessError.GENENRAL, "请填写完毕再提交任务");
        Mission mission = missionService.saveMission(sourceUrl, email, id, token);
        return new Respond(mission);
    }

    @RequestMapping("get")
    public Respond get(@RequestParam(value = "id") Integer id) throws Exception {
        Mission mission = missionService.get(id);
        return new Respond(mission);
    }

    @RequestMapping("delete")
    public Respond delete(@RequestParam(value = "id") Integer id, @RequestParam(value = "token") String token) throws Exception {
        boolean result = missionService.delete(id, token);
        Respond respond = new Respond();
        respond.setData(result);
        return respond;
    }

    @RequestMapping("list")
    public Respond list(@RequestParam(value = "token") String token) throws Exception {
        List<Mission> missionList = missionService.list(token);
        return new Respond(missionList);
    }
}
