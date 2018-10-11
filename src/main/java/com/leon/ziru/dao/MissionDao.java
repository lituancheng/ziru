package com.leon.ziru.dao;

import com.leon.ziru.model.ziru.Tables;
import com.leon.ziru.model.ziru.tables.pojos.Mission;
import com.leon.ziru.model.ziru.tables.records.MissionRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lituancheng on 2018/9/27
 */
@Repository
public class MissionDao extends BaseDao {

    public Mission get(Integer id){
        return ziruDsl.selectFrom(Tables.MISSION).where(Tables.MISSION.ID.eq(id)).fetchOneInto(Mission.class);
    }

    public Mission get(String sourceUrl, Integer userId){
        return ziruDsl
                .selectFrom(Tables.MISSION)
                .where(
                        Tables.MISSION.SOURCE_URL.eq(sourceUrl)
                        .and(Tables.MISSION.USER_ID.eq(userId))
                        .and(Tables.MISSION.STATUS.ne(DELETE))
                )
                .fetchOneInto(Mission.class);
    }

    public List<Mission> getList(Integer userId){
        return ziruDsl.selectFrom(Tables.MISSION)
                .where(
                        Tables.MISSION.USER_ID.eq(userId)
                        .and(Tables.MISSION.STATUS.ne(DELETE))
                )
                .fetchInto(Mission.class);
    }

    public List<Mission> getEnableList(Integer userId){
        return ziruDsl.selectFrom(Tables.MISSION)
                .where(
                        Tables.MISSION.USER_ID.eq(userId)
                        .and(Tables.MISSION.STATUS.eq(NORMAL))
                )
                .fetchInto(Mission.class);
    }

    public List<Mission> getAllEnableList(){
        return ziruDsl.selectFrom(Tables.MISSION)
                .where(Tables.MISSION.STATUS.eq(NORMAL))
                .fetchInto(Mission.class);
    }

    public Integer insert(Mission mission){
        MissionRecord missionRecord = ziruDsl.newRecord(Tables.MISSION, mission);
        MissionRecord result = ziruDsl.insertInto(Tables.MISSION).set(missionRecord).returning(Tables.MISSION.ID).fetchOne();
        return result.getId();
    }

    public boolean update(Mission mission){
        MissionRecord missionRecord = ziruDsl.newRecord(Tables.MISSION, mission);
        int execute = ziruDsl
                .update(Tables.MISSION)
                .set(missionRecord)
                .where(Tables.MISSION.ID.eq(mission.getId()))
                .execute();
        return execute > 0;
    }

    public boolean setClose(Integer id){
        int execute = ziruDsl
                .update(Tables.MISSION)
                .set(Tables.MISSION.STATUS, NO_NORMAL)
                .where(Tables.MISSION.ID.eq(id))
                .execute();
        return execute > 0;
    }

    public boolean delete(Integer id){
        int execute = ziruDsl.update(Tables.MISSION)
                .set(Tables.MISSION.STATUS, DELETE)
                .where(Tables.MISSION.ID.eq(id))
                .execute();
        return execute > 0;
    }

    public boolean sendEmailSuccess(Integer id){
        int execute = ziruDsl.update(Tables.MISSION)
                .set(Tables.MISSION.EMAIL_STATUS, 1)
                .where(Tables.MISSION.ID.eq(id))
                .execute();
        return execute > 0;
    }

    public boolean sendTemplateSuccess(Integer id){
        int execute = ziruDsl.update(Tables.MISSION)
                .set(Tables.MISSION.TEMPLATE_STATUS, 1)
                .where(Tables.MISSION.ID.eq(id))
                .execute();
        return execute > 0;
    }

    public boolean sendSmsSuccess(Integer id){
        int execute = ziruDsl.update(Tables.MISSION)
                .set(Tables.MISSION.SMS_STATUS, 1)
                .where(Tables.MISSION.ID.eq(id))
                .execute();
        return execute > 0;
    }
}
