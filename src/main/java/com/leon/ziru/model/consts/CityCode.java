package com.leon.ziru.model.consts;

public enum  CityCode {

    BJ("BJ", "110000"), SH("SH", "310000"), SZ("SZ", "440300"), GZ("GZ", "440100"), //北京，上海，深圳，广州
    HZ("HZ", "330100"), NJ("NJ", "320100"), WH("WH", "420100"), CD("CD", "510100"), //杭州，南京，武汉，成都
    TJ("TJ", "120000")  //天津
    ;

    CityCode(String name, String code){
        this.name = name;
        this.code = code;
    }

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static CityCode codeOf(String code){
        switch (code){
            case "BJ":
                return BJ;
            case "SH":
                return SH;
            case "SZ":
                return SZ;
            case "GZ":
                return GZ;
            case "HZ":
                return HZ;
            case "NJ":
                return NJ;
            case "WH":
                return WH;
            case "CD":
                return CD;
            case "TJ":
                return TJ;
            default:
                return null;
        }
    }
}
