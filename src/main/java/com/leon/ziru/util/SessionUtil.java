package com.leon.ziru.util;

import com.leon.ziru.model.session.SessionUser;

import java.util.UUID;

public class SessionUtil {

    private static ExpiryMap<String, SessionUser> sessionMap = new ExpiryMap<>();

    public static String put(SessionUser sessionUser){
        String _3rdKey = UUID.randomUUID().toString().replace("-", "");
        sessionMap.put(_3rdKey, sessionUser, 1000 * 7200);
        return _3rdKey;
    }

    public static SessionUser get(String _3rdKey){
        return sessionMap.get(_3rdKey);
    }
}
