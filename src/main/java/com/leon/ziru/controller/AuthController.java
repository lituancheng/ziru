package com.leon.ziru.controller;

import com.leon.ziru.model.Respond;
import com.leon.ziru.service.AuthService;
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
        String _3rdKey = authService.login(code);
        Respond respond = new Respond();
        respond.setData(_3rdKey);
        return new Respond(respond);
    }
}
