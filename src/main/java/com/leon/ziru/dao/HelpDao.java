package com.leon.ziru.dao;

import com.leon.ziru.model.ziru.Tables;
import com.leon.ziru.model.ziru.tables.pojos.HelpPackage;
import com.leon.ziru.model.ziru.tables.records.HelpPackageRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HelpDao extends BaseDao {

    public Integer add(HelpPackage helpPackage){
        HelpPackageRecord helpPackageRecord = ziruDsl.newRecord(Tables.HELP_PACKAGE, helpPackage);
        HelpPackageRecord result = ziruDsl.insertInto(Tables.HELP_PACKAGE).set(helpPackageRecord).returning(Tables.HELP_PACKAGE.ID).fetchOne();
        return result.getId();
    }

    public List<HelpPackage> list(Integer missionId){
        return ziruDsl.selectFrom(Tables.HELP_PACKAGE)
                .where(Tables.HELP_PACKAGE.MISSION_ID.eq(missionId))
                .orderBy(Tables.HELP_PACKAGE.CREATE_TIME.desc())
                .fetchInto(HelpPackage.class);
    }
}
