package com.leon.ziru.util;

import com.leon.ziru.exception.BusinessError;
import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.model.session.SessionUser;

import java.util.UUID;

public class SessionUtil {

    private static ExpiryMap<String, SessionUser> sessionMap = new ExpiryMap<>();

    public static String put(SessionUser sessionUser){
        String token = UUID.randomUUID().toString().replace("-", "");
        sessionMap.put(token, sessionUser, 1000 * 72000);
        return token;
    }

    public static SessionUser get(String token){
        SessionUser sessionUser = sessionMap.get(token);
        if(sessionUser == null)
            throw new BusinessException(BusinessError.AUTH_LOGIN_INVALID);
        return sessionUser;
    }

    public static Integer getUserId(String token){
        return get(token).userId;
    }
}
