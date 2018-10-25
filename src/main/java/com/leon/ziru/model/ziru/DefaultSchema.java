/*
 * This file is generated by jOOQ.
*/
package com.leon.ziru.model.ziru;


import com.leon.ziru.model.ziru.tables.Advice;
import com.leon.ziru.model.ziru.tables.CrawlerErrorInfo;
import com.leon.ziru.model.ziru.tables.HelpPackage;
import com.leon.ziru.model.ziru.tables.Mission;
import com.leon.ziru.model.ziru.tables.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = 501305295;

    /**
     * The reference instance of <code></code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>advice</code>.
     */
    public final Advice ADVICE = com.leon.ziru.model.ziru.tables.Advice.ADVICE;

    /**
     * The table <code>crawler_error_info</code>.
     */
    public final CrawlerErrorInfo CRAWLER_ERROR_INFO = com.leon.ziru.model.ziru.tables.CrawlerErrorInfo.CRAWLER_ERROR_INFO;

    /**
     * The table <code>help_package</code>.
     */
    public final HelpPackage HELP_PACKAGE = com.leon.ziru.model.ziru.tables.HelpPackage.HELP_PACKAGE;

    /**
     * The table <code>mission</code>.
     */
    public final Mission MISSION = com.leon.ziru.model.ziru.tables.Mission.MISSION;

    /**
     * The table <code>user</code>.
     */
    public final User USER = com.leon.ziru.model.ziru.tables.User.USER;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Advice.ADVICE,
            CrawlerErrorInfo.CRAWLER_ERROR_INFO,
            HelpPackage.HELP_PACKAGE,
            Mission.MISSION,
            User.USER);
    }
}
