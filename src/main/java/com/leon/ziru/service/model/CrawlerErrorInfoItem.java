package com.leon.ziru.service.model;

import com.leon.ziru.model.ziru.tables.pojos.CrawlerErrorInfo;

/**
 * Created by lituancheng on 2018/10/25
 */
public class CrawlerErrorInfoItem extends CrawlerErrorInfo {

    private String roomName;
    private String sourceUrl;

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
}
