/*
 * This file is generated by jOOQ.
*/
package com.leon.ziru.model.ziru.tables;


import com.leon.ziru.model.ziru.DefaultSchema;
import com.leon.ziru.model.ziru.Indexes;
import com.leon.ziru.model.ziru.Keys;
import com.leon.ziru.model.ziru.tables.records.MissionRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Mission extends TableImpl<MissionRecord> {

    private static final long serialVersionUID = 1141661013;

    /**
     * The reference instance of <code>mission</code>
     */
    public static final Mission MISSION = new Mission();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MissionRecord> getRecordType() {
        return MissionRecord.class;
    }

    /**
     * The column <code>mission.id</code>.
     */
    public final TableField<MissionRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>mission.source_url</code>. 房源链接
     */
    public final TableField<MissionRecord, String> SOURCE_URL = createField("source_url", org.jooq.impl.SQLDataType.VARCHAR(256).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "房源链接");

    /**
     * The column <code>mission.user_id</code>.
     */
    public final TableField<MissionRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>mission.room_name</code>. 房源名称
     */
    public final TableField<MissionRecord, String> ROOM_NAME = createField("room_name", org.jooq.impl.SQLDataType.VARCHAR(256).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "房源名称");

    /**
     * The column <code>mission.room_status</code>. 房屋状态
     */
    public final TableField<MissionRecord, Integer> ROOM_STATUS = createField("room_status", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "房屋状态");

    /**
     * The column <code>mission.email</code>. 通知邮箱
     */
    public final TableField<MissionRecord, String> EMAIL = createField("email", org.jooq.impl.SQLDataType.VARCHAR(128).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "通知邮箱");

    /**
     * The column <code>mission.form_id</code>.
     */
    public final TableField<MissionRecord, String> FORM_ID = createField("form_id", org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>mission.status</code>. -1 已删除 0 运行中 1 已停止
     */
    public final TableField<MissionRecord, Integer> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "-1 已删除 0 运行中 1 已停止");

    /**
     * The column <code>mission.email_status</code>. 0 未发送邮件 1 已发送
     */
    public final TableField<MissionRecord, Integer> EMAIL_STATUS = createField("email_status", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "0 未发送邮件 1 已发送");

    /**
     * The column <code>mission.template_status</code>. 0 未发送模板 1 已发送
     */
    public final TableField<MissionRecord, Integer> TEMPLATE_STATUS = createField("template_status", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "0 未发送模板 1 已发送");

    /**
     * The column <code>mission.sms_status</code>. 0 未发送短信 1 已发送
     */
    public final TableField<MissionRecord, Integer> SMS_STATUS = createField("sms_status", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "0 未发送短信 1 已发送");

    /**
     * The column <code>mission.level</code>. 1 低速 2 中速 3 快速 4 高速 5 VIP
     */
    public final TableField<MissionRecord, Integer> LEVEL = createField("level", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("1", org.jooq.impl.SQLDataType.INTEGER)), this, "1 低速 2 中速 3 快速 4 高速 5 VIP");

    /**
     * The column <code>mission.create_time</code>.
     */
    public final TableField<MissionRecord, Timestamp> CREATE_TIME = createField("create_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>mission.update_time</code>.
     */
    public final TableField<MissionRecord, Timestamp> UPDATE_TIME = createField("update_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>mission</code> table reference
     */
    public Mission() {
        this(DSL.name("mission"), null);
    }

    /**
     * Create an aliased <code>mission</code> table reference
     */
    public Mission(String alias) {
        this(DSL.name(alias), MISSION);
    }

    /**
     * Create an aliased <code>mission</code> table reference
     */
    public Mission(Name alias) {
        this(alias, MISSION);
    }

    private Mission(Name alias, Table<MissionRecord> aliased) {
        this(alias, aliased, null);
    }

    private Mission(Name alias, Table<MissionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.MISSION_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<MissionRecord, Integer> getIdentity() {
        return Keys.IDENTITY_MISSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<MissionRecord> getPrimaryKey() {
        return Keys.KEY_MISSION_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<MissionRecord>> getKeys() {
        return Arrays.<UniqueKey<MissionRecord>>asList(Keys.KEY_MISSION_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mission as(String alias) {
        return new Mission(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mission as(Name alias) {
        return new Mission(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Mission rename(String name) {
        return new Mission(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Mission rename(Name name) {
        return new Mission(name, null);
    }
}
