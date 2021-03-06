/*
 * This file is generated by jOOQ.
*/
package com.leon.ziru.model.ziru.tables.records;


import com.leon.ziru.model.ziru.tables.HelpPackage;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


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
public class HelpPackageRecord extends UpdatableRecordImpl<HelpPackageRecord> implements Record6<Integer, Integer, Integer, String, Timestamp, Timestamp> {

    private static final long serialVersionUID = 1780719530;

    /**
     * Setter for <code>help_package.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>help_package.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>help_package.mission_id</code>.
     */
    public void setMissionId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>help_package.mission_id</code>.
     */
    public Integer getMissionId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>help_package.package_num</code>.
     */
    public void setPackageNum(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>help_package.package_num</code>.
     */
    public Integer getPackageNum() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>help_package.open_id</code>.
     */
    public void setOpenId(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>help_package.open_id</code>.
     */
    public String getOpenId() {
        return (String) get(3);
    }

    /**
     * Setter for <code>help_package.create_time</code>.
     */
    public void setCreateTime(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>help_package.create_time</code>.
     */
    public Timestamp getCreateTime() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>help_package.update_time</code>.
     */
    public void setUpdateTime(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>help_package.update_time</code>.
     */
    public Timestamp getUpdateTime() {
        return (Timestamp) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, Integer, Integer, String, Timestamp, Timestamp> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, Integer, Integer, String, Timestamp, Timestamp> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return HelpPackage.HELP_PACKAGE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return HelpPackage.HELP_PACKAGE.MISSION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return HelpPackage.HELP_PACKAGE.PACKAGE_NUM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return HelpPackage.HELP_PACKAGE.OPEN_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return HelpPackage.HELP_PACKAGE.CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return HelpPackage.HELP_PACKAGE.UPDATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getMissionId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getPackageNum();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getOpenId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component5() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component6() {
        return getUpdateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getMissionId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getPackageNum();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getOpenId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getUpdateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpPackageRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpPackageRecord value2(Integer value) {
        setMissionId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpPackageRecord value3(Integer value) {
        setPackageNum(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpPackageRecord value4(String value) {
        setOpenId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpPackageRecord value5(Timestamp value) {
        setCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpPackageRecord value6(Timestamp value) {
        setUpdateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpPackageRecord values(Integer value1, Integer value2, Integer value3, String value4, Timestamp value5, Timestamp value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HelpPackageRecord
     */
    public HelpPackageRecord() {
        super(HelpPackage.HELP_PACKAGE);
    }

    /**
     * Create a detached, initialised HelpPackageRecord
     */
    public HelpPackageRecord(Integer id, Integer missionId, Integer packageNum, String openId, Timestamp createTime, Timestamp updateTime) {
        super(HelpPackage.HELP_PACKAGE);

        set(0, id);
        set(1, missionId);
        set(2, packageNum);
        set(3, openId);
        set(4, createTime);
        set(5, updateTime);
    }
}
