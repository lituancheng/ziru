package com.leon.ziru.controller.config;

import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.model.Respond;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lituancheng on 2018/9/26
 */
@ControllerAdvice
@ResponseBody
public final class RequestExceptionHandler {

    /**
     * 参数有误
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Object methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new Object();
    }

    @ExceptionHandler({BusinessException.class})
    public Respond businessException(BusinessException ex) {
        return new Respond(ex);
    }

    @ExceptionHandler({Exception.class})
    public Respond handleException(HttpServletRequest req, Exception ex) {
        return new Respond(ex);
    }
}