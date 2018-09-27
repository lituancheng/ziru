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

    private static final long serialVersionUID = -279215846;

    private Integer   id;
    private String    sourceUrl;
    private Integer   userId;
    private String    roomName;
    private String    roomStatus;
    private Integer   status;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Mission() {}

    public Mission(Mission value) {
        this.id = value.id;
        this.sourceUrl = value.sourceUrl;
        this.userId = value.userId;
        this.roomName = value.roomName;
        this.roomStatus = value.roomStatus;
        this.status = value.status;
        this.createTime = value.createTime;
        this.updateTime = value.updateTime;
    }

    public Mission(
        Integer   id,
        String    sourceUrl,
        Integer   userId,
        String    roomName,
        String    roomStatus,
        Integer   status,
        Timestamp createTime,
        Timestamp updateTime
    ) {
        this.id = id;
        this.sourceUrl = sourceUrl;
        this.userId = userId;
        this.roomName = roomName;
        this.roomStatus = roomStatus;
        this.status = status;
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

    public String getRoomStatus() {
        return this.roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(", ").append(status);
        sb.append(", ").append(createTime);
        sb.append(", ").append(updateTime);

        sb.append(")");
        return sb.toString();
    }
}
