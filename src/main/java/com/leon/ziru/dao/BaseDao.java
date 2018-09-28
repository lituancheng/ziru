package com.leon.ziru.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by lituancheng on 2018/9/26
 */
public abstract class BaseDao {

    public static final int DELETE = -1;
    public static final int NORMAL = 0;
    public static final int NO_NORMAL = 1;

    @Autowired
    @Qualifier("ziruDsl")
    protected DSLContext ziruDsl;
}