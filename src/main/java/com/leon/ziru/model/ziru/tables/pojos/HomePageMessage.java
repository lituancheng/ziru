/*
 * This file is generated by jOOQ.
*/
package com.leon.ziru.model.ziru.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HomePageMessage implements Serializable {

    private static final long serialVersionUID = -499790471;

    private Integer   id;
    private String    msgP;
    private String    msgA;
    private String    iMsg;
    private Integer   enable;
    private Timestamp createTime;
    private Timestamp updateTime;

    public HomePageMessage() {}

    public HomePageMessage(HomePageMessage value) {
        this.id = value.id;
        this.msgP = value.msgP;
        this.msgA = value.msgA;
        this.iMsg = value.iMsg;
        this.enable = value.enable;
        this.createTime = value.createTime;
        this.updateTime = value.updateTime;
    }

    public HomePageMessage(
        Integer   id,
        String    msgP,
        String    msgA,
        String    iMsg,
        Integer   enable,
        Timestamp createTime,
        Timestamp updateTime
    ) {
        this.id = id;
        this.msgP = msgP;
        this.msgA = msgA;
        this.iMsg = iMsg;
        this.enable = enable;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsgP() {
        return this.msgP;
    }

    public void setMsgP(String msgP) {
        this.msgP = msgP;
    }

    public String getMsgA() {
        return this.msgA;
    }

    public void setMsgA(String msgA) {
        this.msgA = msgA;
    }

    public String getIMsg() {
        return this.iMsg;
    }

    public void setIMsg(String iMsg) {
        this.iMsg = iMsg;
    }

    public Integer getEnable() {
        return this.enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HomePageMessage (");

        sb.append(id);
        sb.append(", ").append(msgP);
        sb.append(", ").append(msgA);
        sb.append(", ").append(iMsg);
        sb.append(", ").append(enable);
        sb.append(", ").append(createTime);
        sb.append(", ").append(updateTime);

        sb.append(")");
        return sb.toString();
    }
}