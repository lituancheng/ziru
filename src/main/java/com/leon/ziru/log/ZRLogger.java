package com.leon.ziru.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by lituancheng on 2018/9/26
 */
public class ZRLogger {

    //请求日志 应当仅由ReqInterceptor输出
    public static Logger reqLog = LogManager.getLogger("reqLogger");
    //服务状态日志
    public static Logger statusLog = LogManager.getLogger("statusLogger");

    public static Logger errorLog = LogManager.getLogger("errorLogger");
    public static Logger infoLog = LogManager.getLogger("infoLogger");
    public static Logger debugLog = LogManager.getLogger("debugLogger");

    //root日志，所有代码中未定义的Logger会打到这里
    //例如    LogManager.getLogger("unknownLogger");
    //       LogManager.getLogger(this.getClass().getName());
    public static Logger messLog = LogManager.getLogger("messLogger");
}
