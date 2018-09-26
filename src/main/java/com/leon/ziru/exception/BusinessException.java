package com.leon.ziru.exception;

public class BusinessException extends RuntimeException {

    private int errorCode;
    private String msg;
    private String detailMsg;

    public BusinessException(BusinessError businessError) {
        this.errorCode = businessError.getErrorCode();
        this.msg = businessError.getMsg();
    }

    public BusinessException(BusinessError businessError, String detailMsg) {
        this.errorCode = businessError.getErrorCode();
        this.msg = businessError.getMsg();
        this.detailMsg = detailMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public String getDetailMsg() {
        return detailMsg;
    }
}
