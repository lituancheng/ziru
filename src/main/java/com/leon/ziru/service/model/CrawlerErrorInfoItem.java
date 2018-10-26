package com.leon.ziru.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leon.ziru.model.ziru.tables.pojos.CrawlerErrorInfo;

import java.sql.Timestamp;

/**
 * Created by lituancheng on 2018/10/25
 */
public class CrawlerErrorInfoItem extends CrawlerErrorInfo {

    private String roomName;
    private String sourceUrl;
    private String statusStr;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Override
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Timestamp getCreateTime() {
        return super.getCreateTime();
    }

    @Override
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Timestamp getUpdateTime() {
        return super.getUpdateTime();
    }
}
