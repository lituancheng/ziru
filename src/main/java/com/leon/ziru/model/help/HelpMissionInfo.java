package com.leon.ziru.model.help;

import java.util.List;

public class HelpMissionInfo {

    private int sumPackage;
    private boolean haveHelp;
    private int progressPercent;
    private String currentSpeed;
    private List<HelperInfo> helperInfoList;

    public int getSumPackage() {
        return sumPackage;
    }

    public void setSumPackage(int sumPackage) {
        this.sumPackage = sumPackage;
    }

    public boolean isHaveHelp() {
        return haveHelp;
    }

    public void setHaveHelp(boolean haveHelp) {
        this.haveHelp = haveHelp;
    }

    public int getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(int progressPercent) {
        this.progressPercent = progressPercent;
    }

    public String getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(String currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public List<HelperInfo> getHelperInfoList() {
        return helperInfoList;
    }

    public void setHelperInfoList(List<HelperInfo> helperInfoList) {
        this.helperInfoList = helperInfoList;
    }

    public static class HelperInfo{
        private boolean isMe;
        private int num;
        private String createTime;

        public HelperInfo(boolean isMe, int num, String createTime) {
            this.isMe = isMe;
            this.num = num;
            this.createTime = createTime;
        }

        public boolean isMe() {
            return isMe;
        }

        public void setMe(boolean me) {
            isMe = me;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
