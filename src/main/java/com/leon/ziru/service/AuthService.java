package com.leon.ziru.service;

import com.leon.ziru.exception.BusinessError;
import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.model.auth.LoginResp;
import com.leon.ziru.model.session.SessionUser;
import com.leon.ziru.model.ziru.tables.pojos.User;
import com.leon.ziru.util.HttpClientUtil;
import com.leon.ziru.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.leon.ziru.model.consts.WxKeys.APPID;
import static com.leon.ziru.model.consts.WxKeys.SECRET;

@Service
public class AuthService {

    private static final String CODE_2_SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Autowired
    private UserService userService;

    public String login(String code){
        try {
            String openid = getOpenId(code);
            Integer userId;
            User user = userService.getByOpenId(openid);
            if(user == null){
                User newUser = new User();
                newUser.setOpenId(openid);
                userId = userService.createNewUser(newUser);
            }else {
                userId = user.getId();
            }
            return SessionUtil.put(new SessionUser(userId, openid));
        } catch (Exception e) {
            throw new BusinessException(BusinessError.AUTH_LOGIN_FAIL);
        }
    }

    public String getOpenId(String code){
        try {
            LoginResp loginResp = HttpClientUtil.httpGet(String.format(CODE_2_SESSION, APPID, SECRET, code), LoginResp.class);
            String openid = loginResp.openid;
            if(StringUtils.isEmpty(openid))
                throw new BusinessException(BusinessError.GENENRAL, "登录状态异常，请稍后重试");
            return openid;
        } catch (Exception e) {
            throw new BusinessException(BusinessError.GENENRAL, "获取openid失败");
        }
    }
}
