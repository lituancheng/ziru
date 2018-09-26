package com.leon.ziru.controller;

import com.leon.ziru.model.Respond;
import com.leon.ziru.service.ZiruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ziru")
public class ZiruController {

    @Autowired
    private ZiruService ziruService;

    @RequestMapping("test")
    public void addMission(String code){
        System.out.println(code);
    }
}
