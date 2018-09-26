package com.leon.ziru.exception;

public enum BusinessError{

    GENENRAL(Module.GENERAL, 0, "通用异常"),

    AUTH_LOGIN_FAIL(Module.AUTH, 0, "登录失败，请稍后重试"),

    ZIRU_GET_DATA_ERROR(Module.ZIRU, 1, "获取自如数据异常"),


    ;

    BusinessError(Module module, int code, String msg){
        this.errorCode = 1 * 1000 * 1000 + module.ordinal() * 1000 + code;
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }

    private int errorCode;
    private String msg;

    enum Module{
        GENERAL, AUTH, ZIRU, USER
    }
}
