package com.boot.demo.config.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sunlichuan
 */
@Slf4j
@Configuration
public class DataSourceConfiguration {

    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    @DependsOn({"master", "slave"})
    public AbstractRoutingDataSource getRoutingDataSource(@Qualifier("master") DataSource masterSource,
                                                          @Qualifier("slave") DataSource slaveSource) {
        log.info("create routing datasource...");
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>(2);
        dsMap.put("master", masterSource);
        dsMap.put("slave", slaveSource);
        // 动态数据源
        AbstractRoutingDataSource routing = new DynamicDataSource();
        // 默认数据源master
        routing.setDefaultTargetDataSource(master());
        routing.setTargetDataSources(dsMap);

        return routing;
    }
}
