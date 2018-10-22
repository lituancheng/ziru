package com.leon.ziru.service;

import com.google.common.collect.Lists;
import com.leon.ziru.dao.HelpDao;
import com.leon.ziru.dao.MissionDao;
import com.leon.ziru.model.consts.SpeedLevel;
import com.leon.ziru.model.help.HelpMissionInfo;
import com.leon.ziru.model.ziru.tables.pojos.HelpPackage;
import com.leon.ziru.model.ziru.tables.pojos.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class HelpService {

    @Autowired
    private HelpDao helpDao;

    @Autowired
    private MissionDao missionDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");

    @Transactional
    public HelpMissionInfo add(Integer missionId, Integer packageNum, String openId){
        HelpPackage helpPackage = new HelpPackage();
        helpPackage.setMissionId(missionId);
        helpPackage.setPackageNum(packageNum);
        helpPackage.setOpenId(openId);
        helpDao.add(helpPackage);
        HelpMissionInfo helpMissionInfo = helpMissionInfo(missionId, openId);
        int sumPackage = helpMissionInfo.getSumPackage();
        int level = (sumPackage / 20) + 1;
        level = level > 5 ? 5 : level;
        missionDao.setLevel(missionId, level);
        SpeedLevel speedLevel = SpeedLevel.levelOf(level);
        helpMissionInfo.setCurrentSpeed(speedLevel.getValue());
        helpMissionInfo.setProgressPercent(speedLevel.getPercent());
        return helpMissionInfo;
    }

    public HelpMissionInfo helpMissionInfo(Integer missionId, String openId){
        List<HelpPackage> packageList = helpDao.list(missionId);
        boolean haveHelp = false;
        int sumPackage = 0;
        List<HelpMissionInfo.HelperInfo> helperInfoList = Lists.newArrayList();
        for(HelpPackage hp : packageList){
            sumPackage += hp.getPackageNum();
            if(hp.getOpenId().equals(openId)){
                haveHelp = true;
            }
            helperInfoList.add(
                    new HelpMissionInfo.HelperInfo(hp.getOpenId().equals(openId), hp.getPackageNum(), sdf.format(hp.getCreateTime()))
            );
        }
        HelpMissionInfo hmInfo = new HelpMissionInfo();
        hmInfo.setSumPackage(sumPackage);
        hmInfo.setHaveHelp(haveHelp);

        Mission mission = missionDao.get(missionId);
        SpeedLevel speedLevel = SpeedLevel.levelOf(mission.getLevel());
        hmInfo.setCurrentSpeed(speedLevel.getValue());
        hmInfo.setProgressPercent(speedLevel.getPercent());
        hmInfo.setHelperInfoList(helperInfoList);
        return hmInfo;
    }
}
