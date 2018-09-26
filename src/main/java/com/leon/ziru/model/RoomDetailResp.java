package com.leon.ziru.model;

import java.util.Date;
import java.util.List;

public class RoomDetailResp {

    public Integer error_code;
    public String status;
    public RoomDetailData data;

    public static class RoomDetailData{
        public String id;
        public Integer bedroom; //卧室数量
        public List<Config> config; //房屋配置
        public String face;  //朝向
        public String floor;    //层数
        public String floor_total;  //总层数
        public String house_type;
        public Integer is_duanzu;
        public Integer is_new;
        public Integer is_newsign;
        public Integer is_reserve;
        public MiniProgramShare miniprogram_share;
        public String subway_primary;
        public String status;   //dzz 可入住 ycz 已入住 yxd 已預定 zxpzz 配置中 tzpzz 配置中
        public String will_unrent_date;   //预计释放房源时间，dzz且此字段为空的话说明已释放，dzz不为空说明待释放

        public static class Config{
            public String name;
            public String icon;
            public Integer num;
        }

        public static class MiniProgramShare{
            public Integer active;
            public String title;
            public String url;
        }
    }
}
