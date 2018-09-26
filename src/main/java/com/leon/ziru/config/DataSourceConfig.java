package com.leon.ziru.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.jooq.*;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListener;
import org.jooq.tools.JooqLogger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

import static org.springframework.transaction.TransactionDefinition.PROPAGATION_NESTED;

/**
 * Created by lituancheng on 2018/9/26
 */
@Configuration
public class DataSourceConfig {

    @Value("${db.ziru.ip}")
    private String dbZRIp;
    @Value("${db.ziru.name}")
    private String dbZRName;
    @Value("${db.ziru.user}")
    private String dbZRUserName;
    @Value("${db.ziru.password}")
    private String dbZRPassword;


    @Bean(name = "ziruDataSource")
    @ConfigurationProperties(prefix = "datasource.ziru")
    @Primary
    public DataSource ziruDataSource() throws PropertyVetoException {
        return getDataSource(dbZRIp, dbZRName, dbZRUserName, dbZRPassword);
    }


    private DataSource getDataSource(String dbIp, String dbName, String dbUserName, String dbPassword) throws PropertyVetoException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setDriverClass("com.mysql.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://" + dbIp + "/" + dbName
                + "?characterEncoding=utf-8" + "&autoReconnect=true" + "&useSSL=false"
                + "&maxReconnects=2" + "&failOverReadOnly=false"
                + "&connectTimeout=1000" + "&socketTimeout=300000";
        ds.setJdbcUrl(jdbcUrl);
        ds.setUser(dbUserName);
        ds.setPassword(dbPassword);
        ds.setMinPoolSize(1);
        ds.setMaxPoolSize(40);
        ds.setInitialPoolSize(2);
        ds.setMaxIdleTime(1800);
        ds.setAcquireIncrement(3);
        ds.setMaxStatements(5000);
        ds.setCheckoutTimeout(5000);
        ds.setAcquireRetryAttempts(20);
        ds.setAcquireRetryDelay(100);
        ds.setAutoCommitOnClose(false);
        ds.setTestConnectionOnCheckout(true);
        ds.setTestConnectionOnCheckin(false);
        ds.setIdleConnectionTestPeriod(300);
        ds.setPreferredTestQuery("SELECT 1");
        return ds;
    }

    @Bean(name = "ziruTranManager")
    @Primary
    public DataSourceTransactionManager ziruTransactionManager(@Qualifier("ziruDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ziruTranProvider")
    @Primary
    public TransactionProvider ziruTransactionProvider(@Qualifier(value = "ziruTranManager") DataSourceTransactionManager tranManager) {
        return new SpringTransactionProvider(tranManager);
    }

    @Bean(name = "ziruConProvider")
    @Primary
    public ConnectionProvider ziruConnectionProvider(@Qualifier("ziruDataSource") DataSource dataSource) {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
    }

    @Bean(name = "ziruConfig")
    @Primary
    public org.jooq.Configuration ziruJooqConfig(@Qualifier("ziruConProvider") ConnectionProvider connectionProvider,
                                                     @Qualifier(value = "ziruTranProvider") TransactionProvider transactionProvider, ExecuteListenerProvider executeListenerProvider) {
        return new DefaultConfiguration() //
                .derive(connectionProvider) //
                .derive(transactionProvider) //
                .derive(executeListenerProvider) //
                .derive(SQLDialect.MYSQL);
    }

    @Bean(name = "ziruDsl")
    @Primary
    public DSLContext ziruDsl(@Qualifier(value = "ziruConfig") org.jooq.Configuration config) {
        return new DefaultDSLContext(config);
    }

}


class ExceptionTranslator extends DefaultExecuteListener {

    /**
     * Generated UID
     */
    private static final long serialVersionUID = -2450323227461061152L;

    @Override
    public void exception(ExecuteContext ctx) {

        // [#4391] Translate only SQLExceptions
        if (ctx.sqlException() != null) {
            SQLDialect dialect = ctx.dialect();
            SQLExceptionTranslator translator = (dialect != null)
                    ? new SQLErrorCodeSQLExceptionTranslator(dialect.thirdParty().springDbName())
                    : new SQLStateSQLExceptionTranslator();

            ctx.exception(translator.translate("jOOQ", ctx.sql(), ctx.sqlException()));
        }
    }
}


class SpringTransactionProvider implements TransactionProvider {

    private static final JooqLogger log = JooqLogger.getLogger(SpringTransactionProvider.class);

    DataSourceTransactionManager txMgr;

    public SpringTransactionProvider(DataSourceTransactionManager txMgr) {
        this.txMgr = txMgr;
    }

    @Override
    public void begin(TransactionContext ctx) {
        log.info("Begin transaction");

        // This TransactionProvider behaves like jOOQ's DefaultTransactionProvider,
        // which supports nested transactions using Savepoints
        TransactionStatus tx = txMgr.getTransaction(new DefaultTransactionDefinition(PROPAGATION_NESTED));
        ctx.transaction(new SpringTransaction(tx));
    }

    @Override
    public void commit(TransactionContext ctx) {
        log.info("commit transaction");

        txMgr.commit(((SpringTransaction) ctx.transaction()).tx);
    }

    @Override
    public void rollback(TransactionContext ctx) {
        log.info("rollback transaction");

        txMgr.rollback(((SpringTransaction) ctx.transaction()).tx);
    }
}

class SpringTransaction implements Transaction {
    final TransactionStatus tx;

    SpringTransaction(TransactionStatus tx) {
        this.tx = tx;
    }
}
