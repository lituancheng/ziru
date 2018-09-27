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
                )
                .fetchOneInto(Mission.class);
    }

    public List<Mission> getList(Integer userId){
        return ziruDsl.selectFrom(Tables.MISSION)
                .where(Tables.MISSION.USER_ID.eq(userId))
                .fetchInto(Mission.class);
    }

    public Integer insert(Mission mission){
        MissionRecord missionRecord = ziruDsl.newRecord(Tables.MISSION, mission);
        MissionRecord result = ziruDsl.insertInto(Tables.MISSION).set(missionRecord).returning(Tables.MISSION.ID).fetchOne();
        return result.getId();
    }
}
