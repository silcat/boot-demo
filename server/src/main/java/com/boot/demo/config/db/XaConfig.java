package com.boot.demo.config.db;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(name = "xa.switch", havingValue = "false")
public class XaConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.master0")
    public DataSourceProperties masterProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.master1")
    public DataSourceProperties slaveProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "master0")
    public DataSource master(@Autowired DataSourceProperties masterProperties) {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(masterProperties.getUrl());
        mysqlXaDataSource.setPassword(masterProperties.getPassword());
        mysqlXaDataSource.setUser(masterProperties.getUsername());
        try {
            mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("master0");
        return xaDataSource;


    }

    @Bean(name = "master1")
    public DataSource slave(@Autowired DataSourceProperties slaveProperties) {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(slaveProperties.getUrl());
        mysqlXaDataSource.setPassword(slaveProperties.getPassword());
        mysqlXaDataSource.setUser(slaveProperties.getUsername());
        try {
            mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("master1");
        return xaDataSource;
    }

}

