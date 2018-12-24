package com.leon.ziru.service;

import com.leon.ziru.dao.HomePageMessageDao;
import com.leon.ziru.model.ziru.tables.pojos.HomePageMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lituancheng on 2018/12/24
 */
@Service
public class HomePageMessageService {

    @Autowired
    private HomePageMessageDao homePageMessageDao;

    public HomePageMessage get(){
        return homePageMessageDao.get();
    }
}
