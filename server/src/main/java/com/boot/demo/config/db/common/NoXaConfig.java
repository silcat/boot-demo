package com.boot.demo.config.db.common;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(name = "noxa.switch", havingValue = "false")
public class NoXaConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSourceProperties db1Properties() {
        return new DataSourceProperties();
    }

    @Bean(name = "db1")
    public DataSource db1(@Autowired DataSourceProperties db1Properties) {
        HikariDataSource dataSource = db1Properties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
        if (StringUtils.hasText(db1Properties.getName())) {
            dataSource.setPoolName(db1Properties.getName());
        }
        return dataSource;
    }


    @Primary
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("db1")DataSource db1,
                                        @Qualifier("shardingDataSource")DataSource shardingDataSource){
        //动态数据源
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.Sharding.name(), shardingDataSource);
        targetDataSources.put(DataSourceType.DB1.name(), db1);
        return new DynamicDataSource(db1, targetDataSources);
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource)  {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionTemplate transactionTemplate(DataSourceTransactionManager dataSourceTransactionManager){
        return new TransactionTemplate(dataSourceTransactionManager);
    }
}

