package com.leon.ziru.model.session;

/**
 * Created by lituancheng on 2018/9/27
 */
public class SessionUser {

    public SessionUser(Integer userId, String openId) {
        this.userId = userId;
        this.openId = openId;
    }

    public Integer userId;
    public String openId;
}
