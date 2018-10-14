package com.leon.ziru.controller.api;

import com.leon.ziru.exception.BusinessError;
import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.model.Respond;
import com.leon.ziru.service.AdviceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/advice")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    @RequestMapping("add")
    public Respond add(@RequestParam(value = "advice") String advice,
                       @RequestParam(value = "token") String token) {
        if(StringUtils.isEmpty(advice))
            throw new BusinessException(BusinessError.GENENRAL, "请填写完毕再提交");
        Integer id = adviceService.add(advice, token);
        return new Respond(id);
    }
}
