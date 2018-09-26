package com.leon.ziru.config;

import com.google.gson.Gson;
import com.leon.ziru.log.ZRLogger;
import com.leon.ziru.model.req.ReqHolder;
import com.leon.ziru.model.req.ReqInfo;
import com.leon.ziru.util.ServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lituancheng on 2018/9/26
 */
@Component
public class ReqInterceptor implements HandlerInterceptor {

    @Autowired
    private Gson gson;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws NoSuchFieldException, IllegalAccessException {
        String reqId = UUID.randomUUID().toString();
        String contextPath = request.getContextPath();
        String sessionId = request.getSession().getId();
        String requestUri = request.getRequestURI();
        String reqPath = requestUri.substring(contextPath.length());
        String method = request.getMethod();
        String reqIp = ServletRequestUtil.getRemoteIp(request);
        String port = ServletRequestUtil.getRemotePort(request);
        Map<String, String[]> parameters = request.getParameterMap();
        ReqInfo reqInfo = ReqInfo.builder()
                .setId(reqId)
                .setIp(reqIp)
                .setPort(port)
                .setSessionId(sessionId)
                .setContextPath(contextPath)
                .setReqPath(reqPath)
                .setMethod(method)
                .setParameters(parameters)
                .build();
        ReqHolder.setReqInfo(reqInfo);

        ZRLogger.reqLog.info("reqInfo:{}", gson.toJson(reqInfo));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {
    }

}
