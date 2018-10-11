package com.leon.ziru.dao;

import com.leon.ziru.model.ziru.Tables;
import com.leon.ziru.model.ziru.tables.pojos.User;
import com.leon.ziru.model.ziru.tables.records.UserRecord;
import org.springframework.stereotype.Repository;

/**
 * Created by lituancheng on 2018/9/27
 */
@Repository
public class UserDao extends BaseDao {

    public User get(Integer id){
        return ziruDsl.selectFrom(Tables.USER).where(Tables.USER.ID.eq(id)).fetchOneInto(User.class);
    }

    public User getByOpenId(String openId){
        return ziruDsl.selectFrom(Tables.USER).where(Tables.USER.OPEN_ID.eq(openId)).fetchOneInto(User.class);
    }

    public Integer insert(User user){
        UserRecord userRecord = ziruDsl.newRecord(Tables.USER, user);
        UserRecord result = ziruDsl.insertInto(Tables.USER).set(userRecord).returning(Tables.USER.ID).fetchOne();
        return result.getId();
    }

    public boolean bindPhone(Integer id, String phone){
        int execute = ziruDsl.update(Tables.USER)
                .set(Tables.USER.PHONE, phone)
                .where(Tables.USER.ID.eq(id))
                .execute();
        return execute > 0;
    }
}
