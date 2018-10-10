package com.leon.ziru.service;

import com.leon.ziru.dao.UserDao;
import com.leon.ziru.model.session.SessionUser;
import com.leon.ziru.model.ziru.tables.pojos.User;
import com.leon.ziru.util.SessionUtil;
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

    public boolean bindPhone(String token, String phone){
        Integer userId = SessionUtil.getUserId(token);
        User user = userDao.get(userId);
//        if(user.get)
        return true;
    }
}
