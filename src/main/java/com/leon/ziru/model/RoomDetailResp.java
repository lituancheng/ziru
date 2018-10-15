package com.leon.ziru.model;

public class RoomDetailResp {

    public Integer error_code;
    public String status;
    public RoomDetailData data;

    public static class RoomDetailData{
        public String id;
        public String name;
        public String status;   //dzz 可入住 ycz 已入住 yxd 已預定 zxpzz 配置中 tzpzz 配置中
    }
}
