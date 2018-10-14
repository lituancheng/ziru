package com.leon.ziru.service;

import com.leon.ziru.dao.AdviceDao;
import com.leon.ziru.model.ziru.tables.pojos.Advice;
import com.leon.ziru.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdviceService {

    @Autowired
    private AdviceDao adviceDao;

    public Integer add(String advice, String token){
        Integer userId = SessionUtil.getUserId(token);
        Advice newAdvice = new Advice();
        newAdvice.setContent(advice);
        newAdvice.setUserId(userId);
        return adviceDao.add(newAdvice);
    }
}
