package com.leon.ziru.service;

import com.leon.ziru.exception.BusinessError;
import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.model.auth.LoginResp;
import com.leon.ziru.util.HttpClientUtil;
import com.leon.ziru.util.SessionUtil;
import org.springframework.stereotype.Service;

import static com.leon.ziru.model.consts.WxKeys.APPID;
import static com.leon.ziru.model.consts.WxKeys.SECRET;

@Service
public class AuthService {

    private static final String CODE_2_SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    public String login(String code){
        try {
            LoginResp loginResp = HttpClientUtil.httpGet(String.format(CODE_2_SESSION, APPID, SECRET, code), LoginResp.class);
            return SessionUtil.put(loginResp.openid);
        } catch (Exception e) {
            throw new BusinessException(BusinessError.AUTH_LOGIN_FAIL);
        }
    }
}
