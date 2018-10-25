package com.leon.ziru.dao;

import com.leon.ziru.model.ziru.Tables;
import com.leon.ziru.model.ziru.tables.pojos.CrawlerErrorInfo;
import com.leon.ziru.service.model.CrawlerErrorInfoItem;
import org.jooq.Condition;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lituancheng on 2018/10/25
 */
@Repository
public class CrawlerErrorInfoDao extends BaseDao {

    public CrawlerErrorInfo getByMissionId(int missionId){
        return ziruDsl.selectFrom(Tables.CRAWLER_ERROR_INFO)
                .where(Tables.CRAWLER_ERROR_INFO.MISSION_ID.eq(missionId))
                .fetchOneInto(CrawlerErrorInfo.class);
    }

    public Integer createErrorInfo(int missionId){
        return ziruDsl.insertInto(
                Tables.CRAWLER_ERROR_INFO,
                Tables.CRAWLER_ERROR_INFO.MISSION_ID,
                Tables.CRAWLER_ERROR_INFO.ERR_COUNT
        )
                .values(missionId, 1)
                .returning(Tables.CRAWLER_ERROR_INFO.ID)
                .fetchOne()
                .getId();
    }

    public boolean addCount(int missionId, int count){
        return ziruDsl.update(Tables.CRAWLER_ERROR_INFO)
                .set(Tables.CRAWLER_ERROR_INFO.ERR_COUNT, count)
                .where(Tables.CRAWLER_ERROR_INFO.MISSION_ID.eq(missionId))
                .execute() > 0;
    }

    public boolean haveNotice(int missionId){
        return ziruDsl.update(Tables.CRAWLER_ERROR_INFO)
                .set(Tables.CRAWLER_ERROR_INFO.STATUS, 1)
                .where(Tables.CRAWLER_ERROR_INFO.MISSION_ID.eq(missionId))
                .execute() > 0;
    }

    public List<CrawlerErrorInfoItem> list(Integer status){
        Condition condition = Tables.CRAWLER_ERROR_INFO.ID.ge(0);
        if(status != null)
            condition = condition.and(Tables.CRAWLER_ERROR_INFO.STATUS.eq(status));
        return ziruDsl.select(
                Tables.CRAWLER_ERROR_INFO.ID,
                Tables.CRAWLER_ERROR_INFO.MISSION_ID,
                Tables.CRAWLER_ERROR_INFO.ERR_COUNT,
                Tables.CRAWLER_ERROR_INFO.STATUS,
                Tables.CRAWLER_ERROR_INFO.CREATE_TIME,
                Tables.CRAWLER_ERROR_INFO.UPDATE_TIME,
                Tables.MISSION.ROOM_NAME,
                Tables.MISSION.SOURCE_URL
        )
                .from(Tables.CRAWLER_ERROR_INFO)
                .leftJoin(Tables.MISSION).on(Tables.MISSION.ID.eq(Tables.CRAWLER_ERROR_INFO.MISSION_ID))
                .where(condition)
                .orderBy(
                        Tables.CRAWLER_ERROR_INFO.STATUS,
                        Tables.CRAWLER_ERROR_INFO.UPDATE_TIME.desc()
                )
                .fetchInto(CrawlerErrorInfoItem.class);
    }
}
