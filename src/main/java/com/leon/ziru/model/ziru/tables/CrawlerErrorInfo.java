/*
 * This file is generated by jOOQ.
*/
package com.leon.ziru.model.ziru.tables;


import com.leon.ziru.model.ziru.DefaultSchema;
import com.leon.ziru.model.ziru.Indexes;
import com.leon.ziru.model.ziru.Keys;
import com.leon.ziru.model.ziru.tables.records.CrawlerErrorInfoRecord;

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
public class CrawlerErrorInfo extends TableImpl<CrawlerErrorInfoRecord> {

    private static final long serialVersionUID = 1984816425;

    /**
     * The reference instance of <code>crawler_error_info</code>
     */
    public static final CrawlerErrorInfo CRAWLER_ERROR_INFO = new CrawlerErrorInfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CrawlerErrorInfoRecord> getRecordType() {
        return CrawlerErrorInfoRecord.class;
    }

    /**
     * The column <code>crawler_error_info.id</code>.
     */
    public final TableField<CrawlerErrorInfoRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>crawler_error_info.mission_id</code>.
     */
    public final TableField<CrawlerErrorInfoRecord, Integer> MISSION_ID = createField("mission_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>crawler_error_info.err_count</code>.
     */
    public final TableField<CrawlerErrorInfoRecord, Integer> ERR_COUNT = createField("err_count", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>crawler_error_info.status</code>. 0 未通知 1 已通知
     */
    public final TableField<CrawlerErrorInfoRecord, Integer> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "0 未通知 1 已通知");

    /**
     * The column <code>crawler_error_info.create_time</code>.
     */
    public final TableField<CrawlerErrorInfoRecord, Timestamp> CREATE_TIME = createField("create_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>crawler_error_info.update_time</code>.
     */
    public final TableField<CrawlerErrorInfoRecord, Timestamp> UPDATE_TIME = createField("update_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>crawler_error_info</code> table reference
     */
    public CrawlerErrorInfo() {
        this(DSL.name("crawler_error_info"), null);
    }

    /**
     * Create an aliased <code>crawler_error_info</code> table reference
     */
    public CrawlerErrorInfo(String alias) {
        this(DSL.name(alias), CRAWLER_ERROR_INFO);
    }

    /**
     * Create an aliased <code>crawler_error_info</code> table reference
     */
    public CrawlerErrorInfo(Name alias) {
        this(alias, CRAWLER_ERROR_INFO);
    }

    private CrawlerErrorInfo(Name alias, Table<CrawlerErrorInfoRecord> aliased) {
        this(alias, aliased, null);
    }

    private CrawlerErrorInfo(Name alias, Table<CrawlerErrorInfoRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.CRAWLER_ERROR_INFO_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CrawlerErrorInfoRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CRAWLER_ERROR_INFO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CrawlerErrorInfoRecord> getPrimaryKey() {
        return Keys.KEY_CRAWLER_ERROR_INFO_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CrawlerErrorInfoRecord>> getKeys() {
        return Arrays.<UniqueKey<CrawlerErrorInfoRecord>>asList(Keys.KEY_CRAWLER_ERROR_INFO_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrawlerErrorInfo as(String alias) {
        return new CrawlerErrorInfo(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrawlerErrorInfo as(Name alias) {
        return new CrawlerErrorInfo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CrawlerErrorInfo rename(String name) {
        return new CrawlerErrorInfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CrawlerErrorInfo rename(Name name) {
        return new CrawlerErrorInfo(name, null);
    }
}
