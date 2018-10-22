/*
 * This file is generated by jOOQ.
*/
package com.leon.ziru.model.ziru.tables;


import com.leon.ziru.model.ziru.DefaultSchema;
import com.leon.ziru.model.ziru.Indexes;
import com.leon.ziru.model.ziru.Keys;
import com.leon.ziru.model.ziru.tables.records.HelpPackageRecord;

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
public class HelpPackage extends TableImpl<HelpPackageRecord> {

    private static final long serialVersionUID = -1413220904;

    /**
     * The reference instance of <code>help_package</code>
     */
    public static final HelpPackage HELP_PACKAGE = new HelpPackage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<HelpPackageRecord> getRecordType() {
        return HelpPackageRecord.class;
    }

    /**
     * The column <code>help_package.id</code>.
     */
    public final TableField<HelpPackageRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>help_package.mission_id</code>.
     */
    public final TableField<HelpPackageRecord, Integer> MISSION_ID = createField("mission_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>help_package.package_num</code>.
     */
    public final TableField<HelpPackageRecord, Integer> PACKAGE_NUM = createField("package_num", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>help_package.open_id</code>.
     */
    public final TableField<HelpPackageRecord, String> OPEN_ID = createField("open_id", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>help_package.create_time</code>.
     */
    public final TableField<HelpPackageRecord, Timestamp> CREATE_TIME = createField("create_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>help_package.update_time</code>.
     */
    public final TableField<HelpPackageRecord, Timestamp> UPDATE_TIME = createField("update_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>help_package</code> table reference
     */
    public HelpPackage() {
        this(DSL.name("help_package"), null);
    }

    /**
     * Create an aliased <code>help_package</code> table reference
     */
    public HelpPackage(String alias) {
        this(DSL.name(alias), HELP_PACKAGE);
    }

    /**
     * Create an aliased <code>help_package</code> table reference
     */
    public HelpPackage(Name alias) {
        this(alias, HELP_PACKAGE);
    }

    private HelpPackage(Name alias, Table<HelpPackageRecord> aliased) {
        this(alias, aliased, null);
    }

    private HelpPackage(Name alias, Table<HelpPackageRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.HELP_PACKAGE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<HelpPackageRecord, Integer> getIdentity() {
        return Keys.IDENTITY_HELP_PACKAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<HelpPackageRecord> getPrimaryKey() {
        return Keys.KEY_HELP_PACKAGE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<HelpPackageRecord>> getKeys() {
        return Arrays.<UniqueKey<HelpPackageRecord>>asList(Keys.KEY_HELP_PACKAGE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpPackage as(String alias) {
        return new HelpPackage(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpPackage as(Name alias) {
        return new HelpPackage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public HelpPackage rename(String name) {
        return new HelpPackage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public HelpPackage rename(Name name) {
        return new HelpPackage(name, null);
    }
}
