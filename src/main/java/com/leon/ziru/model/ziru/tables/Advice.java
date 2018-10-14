/*
 * This file is generated by jOOQ.
*/
package com.leon.ziru.model.ziru.tables;


import com.leon.ziru.model.ziru.DefaultSchema;
import com.leon.ziru.model.ziru.Indexes;
import com.leon.ziru.model.ziru.Keys;
import com.leon.ziru.model.ziru.tables.records.AdviceRecord;

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
public class Advice extends TableImpl<AdviceRecord> {

    private static final long serialVersionUID = -667715435;

    /**
     * The reference instance of <code>advice</code>
     */
    public static final Advice ADVICE = new Advice();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AdviceRecord> getRecordType() {
        return AdviceRecord.class;
    }

    /**
     * The column <code>advice.id</code>.
     */
    public final TableField<AdviceRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>advice.content</code>.
     */
    public final TableField<AdviceRecord, String> CONTENT = createField("content", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>advice.user_id</code>.
     */
    public final TableField<AdviceRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>advice.create_time</code>.
     */
    public final TableField<AdviceRecord, Timestamp> CREATE_TIME = createField("create_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>advice.update_time</code>.
     */
    public final TableField<AdviceRecord, Timestamp> UPDATE_TIME = createField("update_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>advice</code> table reference
     */
    public Advice() {
        this(DSL.name("advice"), null);
    }

    /**
     * Create an aliased <code>advice</code> table reference
     */
    public Advice(String alias) {
        this(DSL.name(alias), ADVICE);
    }

    /**
     * Create an aliased <code>advice</code> table reference
     */
    public Advice(Name alias) {
        this(alias, ADVICE);
    }

    private Advice(Name alias, Table<AdviceRecord> aliased) {
        this(alias, aliased, null);
    }

    private Advice(Name alias, Table<AdviceRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.ADVICE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<AdviceRecord, Integer> getIdentity() {
        return Keys.IDENTITY_ADVICE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AdviceRecord> getPrimaryKey() {
        return Keys.KEY_ADVICE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AdviceRecord>> getKeys() {
        return Arrays.<UniqueKey<AdviceRecord>>asList(Keys.KEY_ADVICE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Advice as(String alias) {
        return new Advice(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Advice as(Name alias) {
        return new Advice(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Advice rename(String name) {
        return new Advice(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Advice rename(Name name) {
        return new Advice(name, null);
    }
}
