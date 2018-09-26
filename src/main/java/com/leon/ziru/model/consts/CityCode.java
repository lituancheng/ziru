package com.leon.ziru.model.consts;

public enum  CityCode {

    BJ("BJ", "110000"), SH("SH", "310000"), SZ("SZ", "440300"); //北京，上海，深圳

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
            default:
                return null;
        }
    }
}
