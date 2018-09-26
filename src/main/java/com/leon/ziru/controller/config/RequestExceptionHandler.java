package com.leon.ziru.controller.config;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
}