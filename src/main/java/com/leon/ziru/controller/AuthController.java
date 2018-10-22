package com.leon.ziru.controller;

import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.model.Respond;
import com.leon.ziru.service.AuthService;
import com.leon.ziru.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("login")
    public Respond login(@RequestParam String code){
        String token = authService.login(code);
        Respond respond = new Respond();
        respond.setData(token);
        return new Respond(respond);
    }

    @RequestMapping("get_openid")
    public Respond getOpenId(@RequestParam String code){
        String openId = authService.getOpenId(code);
        Respond respond = new Respond();
        respond.setData(openId);
        return new Respond(respond);
    }

    @RequestMapping("check_session")
    public Respond checkSession(@RequestParam String token){
        boolean valid = true;
        try {
            SessionUtil.get(token);
        } catch (BusinessException e) {
            valid = false;
        }
        Respond respond = new Respond();
        respond.setData(valid);
        return respond;
    }
}
