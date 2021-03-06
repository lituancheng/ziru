/*
 * This file is generated by jOOQ.
*/
package com.leon.ziru.model.ziru.tables;


import com.leon.ziru.model.ziru.DefaultSchema;
import com.leon.ziru.model.ziru.Indexes;
import com.leon.ziru.model.ziru.Keys;
import com.leon.ziru.model.ziru.tables.records.HomePageMessageRecord;

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
public class HomePageMessage extends TableImpl<HomePageMessageRecord> {

    private static final long serialVersionUID = 2077251872;

    /**
     * The reference instance of <code>home_page_message</code>
     */
    public static final HomePageMessage HOME_PAGE_MESSAGE = new HomePageMessage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<HomePageMessageRecord> getRecordType() {
        return HomePageMessageRecord.class;
    }

    /**
     * The column <code>home_page_message.id</code>.
     */
    public final TableField<HomePageMessageRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>home_page_message.msg_p</code>.
     */
    public final TableField<HomePageMessageRecord, String> MSG_P = createField("msg_p", org.jooq.impl.SQLDataType.VARCHAR(128).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>home_page_message.msg_a</code>.
     */
    public final TableField<HomePageMessageRecord, String> MSG_A = createField("msg_a", org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>home_page_message.i_msg</code>.
     */
    public final TableField<HomePageMessageRecord, String> I_MSG = createField("i_msg", org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>home_page_message.enable</code>.
     */
    public final TableField<HomePageMessageRecord, Integer> ENABLE = createField("enable", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>home_page_message.create_time</code>.
     */
    public final TableField<HomePageMessageRecord, Timestamp> CREATE_TIME = createField("create_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>home_page_message.update_time</code>.
     */
    public final TableField<HomePageMessageRecord, Timestamp> UPDATE_TIME = createField("update_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>home_page_message</code> table reference
     */
    public HomePageMessage() {
        this(DSL.name("home_page_message"), null);
    }

    /**
     * Create an aliased <code>home_page_message</code> table reference
     */
    public HomePageMessage(String alias) {
        this(DSL.name(alias), HOME_PAGE_MESSAGE);
    }

    /**
     * Create an aliased <code>home_page_message</code> table reference
     */
    public HomePageMessage(Name alias) {
        this(alias, HOME_PAGE_MESSAGE);
    }

    private HomePageMessage(Name alias, Table<HomePageMessageRecord> aliased) {
        this(alias, aliased, null);
    }

    private HomePageMessage(Name alias, Table<HomePageMessageRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.HOME_PAGE_MESSAGE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<HomePageMessageRecord, Integer> getIdentity() {
        return Keys.IDENTITY_HOME_PAGE_MESSAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<HomePageMessageRecord> getPrimaryKey() {
        return Keys.KEY_HOME_PAGE_MESSAGE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<HomePageMessageRecord>> getKeys() {
        return Arrays.<UniqueKey<HomePageMessageRecord>>asList(Keys.KEY_HOME_PAGE_MESSAGE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HomePageMessage as(String alias) {
        return new HomePageMessage(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HomePageMessage as(Name alias) {
        return new HomePageMessage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public HomePageMessage rename(String name) {
        return new HomePageMessage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public HomePageMessage rename(Name name) {
        return new HomePageMessage(name, null);
    }
}
