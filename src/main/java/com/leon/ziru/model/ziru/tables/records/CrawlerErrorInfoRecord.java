/*
 * This file is generated by jOOQ.
*/
package com.leon.ziru.model.ziru.tables.records;


import com.leon.ziru.model.ziru.tables.CrawlerErrorInfo;

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
public class CrawlerErrorInfoRecord extends UpdatableRecordImpl<CrawlerErrorInfoRecord> implements Record6<Integer, Integer, Integer, Integer, Timestamp, Timestamp> {

    private static final long serialVersionUID = -1993955541;

    /**
     * Setter for <code>crawler_error_info.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>crawler_error_info.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>crawler_error_info.mission_id</code>.
     */
    public void setMissionId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>crawler_error_info.mission_id</code>.
     */
    public Integer getMissionId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>crawler_error_info.err_count</code>.
     */
    public void setErrCount(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>crawler_error_info.err_count</code>.
     */
    public Integer getErrCount() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>crawler_error_info.status</code>. 0 未通知 1 已通知
     */
    public void setStatus(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>crawler_error_info.status</code>. 0 未通知 1 已通知
     */
    public Integer getStatus() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>crawler_error_info.create_time</code>.
     */
    public void setCreateTime(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>crawler_error_info.create_time</code>.
     */
    public Timestamp getCreateTime() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>crawler_error_info.update_time</code>.
     */
    public void setUpdateTime(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>crawler_error_info.update_time</code>.
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
    public Row6<Integer, Integer, Integer, Integer, Timestamp, Timestamp> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, Integer, Integer, Integer, Timestamp, Timestamp> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return CrawlerErrorInfo.CRAWLER_ERROR_INFO.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return CrawlerErrorInfo.CRAWLER_ERROR_INFO.MISSION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return CrawlerErrorInfo.CRAWLER_ERROR_INFO.ERR_COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return CrawlerErrorInfo.CRAWLER_ERROR_INFO.STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return CrawlerErrorInfo.CRAWLER_ERROR_INFO.CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return CrawlerErrorInfo.CRAWLER_ERROR_INFO.UPDATE_TIME;
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
        return getErrCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getStatus();
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
        return getErrCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getStatus();
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
    public CrawlerErrorInfoRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrawlerErrorInfoRecord value2(Integer value) {
        setMissionId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrawlerErrorInfoRecord value3(Integer value) {
        setErrCount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrawlerErrorInfoRecord value4(Integer value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrawlerErrorInfoRecord value5(Timestamp value) {
        setCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrawlerErrorInfoRecord value6(Timestamp value) {
        setUpdateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrawlerErrorInfoRecord values(Integer value1, Integer value2, Integer value3, Integer value4, Timestamp value5, Timestamp value6) {
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
     * Create a detached CrawlerErrorInfoRecord
     */
    public CrawlerErrorInfoRecord() {
        super(CrawlerErrorInfo.CRAWLER_ERROR_INFO);
    }

    /**
     * Create a detached, initialised CrawlerErrorInfoRecord
     */
    public CrawlerErrorInfoRecord(Integer id, Integer missionId, Integer errCount, Integer status, Timestamp createTime, Timestamp updateTime) {
        super(CrawlerErrorInfo.CRAWLER_ERROR_INFO);

        set(0, id);
        set(1, missionId);
        set(2, errCount);
        set(3, status);
        set(4, createTime);
        set(5, updateTime);
    }
}
