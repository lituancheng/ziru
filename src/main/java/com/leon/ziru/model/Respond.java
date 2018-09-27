package com.leon.ziru.model;

import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.log.ZRLogger;

public class Respond {

    private int code;
    private String msg;
    private String errMsg;
    private Object data;

    public Respond(){
        this.code = 0;
    }

    public Respond(Object data) {
        this();
        this.data = data;
    }

    public Respond(String msg) {
        this();
        this.msg = msg;
    }

    public Respond(BusinessException ex) {
        this.code = ex.getErrorCode();
        this.msg = ex.getMsg();
        this.errMsg = ex.getDetailMsg();
    }

    public Respond(Exception e){
        this.code = -1;
        this.msg = "FAIL";
        StringBuilder errMsg = new StringBuilder();
        for(StackTraceElement s : e.getStackTrace()){
            errMsg.append(s);
        }
        this.errMsg = errMsg.toString();
        ZRLogger.errorLog.error("Exception:", e);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
