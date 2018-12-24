package com.leon.ziru.dao;

import com.leon.ziru.model.ziru.Tables;
import com.leon.ziru.model.ziru.tables.pojos.HomePageMessage;
import com.leon.ziru.model.ziru.tables.records.HomePageMessageRecord;
import org.springframework.stereotype.Repository;

/**
 * Created by lituancheng on 2018/12/24
 */
@Repository
public class HomePageMessageDao extends BaseDao {

    public int create(HomePageMessage homePageMessage){
        HomePageMessageRecord newRecord = ziruDsl.newRecord(Tables.HOME_PAGE_MESSAGE, homePageMessage);
        HomePageMessageRecord result = ziruDsl.insertInto(Tables.HOME_PAGE_MESSAGE).set(newRecord).returning(Tables.HOME_PAGE_MESSAGE.ID).fetchOne();
        return result.getId();
    }

    public HomePageMessage get(){
        return ziruDsl.selectFrom(Tables.HOME_PAGE_MESSAGE).where(Tables.HOME_PAGE_MESSAGE.ENABLE.eq(0)).fetchOneInto(HomePageMessage.class);
    }
}
