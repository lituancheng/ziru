package com.leon.ziru.dao;

import com.leon.ziru.model.ziru.Tables;
import com.leon.ziru.model.ziru.tables.pojos.Advice;
import com.leon.ziru.model.ziru.tables.records.AdviceRecord;
import org.springframework.stereotype.Repository;

@Repository
public class AdviceDao extends BaseDao {

    public Integer add(Advice advice){
        AdviceRecord adviceRecord = ziruDsl.newRecord(Tables.ADVICE, advice);
        AdviceRecord result = ziruDsl.insertInto(Tables.ADVICE).set(adviceRecord).returning(Tables.ADVICE.ID).fetchOne();
        return result.getId();
    }
}
