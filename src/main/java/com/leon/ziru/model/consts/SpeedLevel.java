package com.leon.ziru.model.consts;

public enum SpeedLevel {
    LOW(1, 20, "低速"), MIDDLE(2, 40, "中速"), QUICK(3, 60, "快速"), HIGH(4, 80, "高速"), VIP(5, 100, "VIP");

    private int level;
    private int percent;    //百分比
    private String value;

    SpeedLevel(int level, int percent, String value){
        this.level = level;
        this.percent = percent;
        this.value = value;
    }

    public static SpeedLevel levelOf(int level){
        switch (level){
            case 1:
                return LOW;
            case 2:
                return MIDDLE;
            case 3:
                return QUICK;
            case 4:
                return HIGH;
            default:
                return VIP;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getPercent() {
        return percent;
    }

    public String getValue() {
        return value;
    }
}
