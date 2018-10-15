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
public class Mission implements Serializable {

    private static final long serialVersionUID = -332760494;

    private Integer   id;
    private String    sourceUrl;
    private Integer   userId;
    private String    roomName;
    private Integer   roomStatus;
    private String    email;
    private String    formId;
    private Integer   status;
    private Integer   emailStatus;
    private Integer   templateStatus;
    private Integer   smsStatus;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Mission() {}

    public Mission(Mission value) {
        this.id = value.id;
        this.sourceUrl = value.sourceUrl;
        this.userId = value.userId;
        this.roomName = value.roomName;
        this.roomStatus = value.roomStatus;
        this.email = value.email;
        this.formId = value.formId;
        this.status = value.status;
        this.emailStatus = value.emailStatus;
        this.templateStatus = value.templateStatus;
        this.smsStatus = value.smsStatus;
        this.createTime = value.createTime;
        this.updateTime = value.updateTime;
    }

    public Mission(
        Integer   id,
        String    sourceUrl,
        Integer   userId,
        String    roomName,
        Integer   roomStatus,
        String    email,
        String    formId,
        Integer   status,
        Integer   emailStatus,
        Integer   templateStatus,
        Integer   smsStatus,
        Timestamp createTime,
        Timestamp updateTime
    ) {
        this.id = id;
        this.sourceUrl = sourceUrl;
        this.userId = userId;
        this.roomName = roomName;
        this.roomStatus = roomStatus;
        this.email = email;
        this.formId = formId;
        this.status = status;
        this.emailStatus = emailStatus;
        this.templateStatus = templateStatus;
        this.smsStatus = smsStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRoomStatus() {
        return this.roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFormId() {
        return this.formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEmailStatus() {
        return this.emailStatus;
    }

    public void setEmailStatus(Integer emailStatus) {
        this.emailStatus = emailStatus;
    }

    public Integer getTemplateStatus() {
        return this.templateStatus;
    }

    public void setTemplateStatus(Integer templateStatus) {
        this.templateStatus = templateStatus;
    }

    public Integer getSmsStatus() {
        return this.smsStatus;
    }

    public void setSmsStatus(Integer smsStatus) {
        this.smsStatus = smsStatus;
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
        StringBuilder sb = new StringBuilder("Mission (");

        sb.append(id);
        sb.append(", ").append(sourceUrl);
        sb.append(", ").append(userId);
        sb.append(", ").append(roomName);
        sb.append(", ").append(roomStatus);
        sb.append(", ").append(email);
        sb.append(", ").append(formId);
        sb.append(", ").append(status);
        sb.append(", ").append(emailStatus);
        sb.append(", ").append(templateStatus);
        sb.append(", ").append(smsStatus);
        sb.append(", ").append(createTime);
        sb.append(", ").append(updateTime);

        sb.append(")");
        return sb.toString();
    }
}
