package com.leon.ziru.service;

import com.leon.ziru.dao.UserDao;
import com.leon.ziru.model.ziru.tables.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lituancheng on 2018/9/27
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getByOpenId(String openId){
        return userDao.getByOpenId(openId);
    }

    public Integer createNewUser(User user){
        return userDao.insert(user);
    }
}
