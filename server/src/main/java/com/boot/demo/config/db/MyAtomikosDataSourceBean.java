package com.boot.demo.config.db;

import com.atomikos.datasource.pool.ConnectionFactory;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

@Configuration
@ConditionalOnProperty(name = "atxa.switch", havingValue = "false")
public class MyAtomikosDataSourceBean extends AtomikosDataSourceBean {
    private String url;
    private String password;
    private String username;
    @Override
    protected ConnectionFactory doInit() throws Exception {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(getUrl());
        mysqlXaDataSource.setPassword(getPassword());
        mysqlXaDataSource.setUser(getUsername());
        try {
            mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setXaDataSource(mysqlXaDataSource);
        return super.doInit();

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

