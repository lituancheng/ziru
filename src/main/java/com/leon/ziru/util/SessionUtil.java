package com.leon.ziru.util;

import java.util.UUID;

public class SessionUtil {

    private static ExpiryMap<String, String> sessionMap = new ExpiryMap<>();

    public static String put(String openId){
        String _3rdKey = UUID.randomUUID().toString().replace("-", "");
        sessionMap.put(_3rdKey, openId, 1000 * 7200);
        return _3rdKey;
    }

    public static String get(String _3rdKey){
        return sessionMap.get(_3rdKey);
    }
}
