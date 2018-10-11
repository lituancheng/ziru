package com.leon.ziru.controller.api;

import com.leon.ziru.model.Respond;
import com.leon.ziru.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("get_phone")
    public Respond getPhone(@RequestParam(value = "token") String token){
        String phone = userService.getPhone(token);
        Respond respond = new Respond();
        respond.setData(phone);
        return respond;
    }

    @RequestMapping("bind_phone")
    public Respond bindPhone(@RequestParam(value = "token") String token, @RequestParam(value = "phone") String phone){
        boolean result = userService.bindPhone(token, phone);
        return new Respond(result);
    }
}
