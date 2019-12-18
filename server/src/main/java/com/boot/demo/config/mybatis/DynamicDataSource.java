package com.boot.demo.config.mybatis;

import com.boot.demo.annotation.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * @author sunlichuan
 * Created by sunlichuan on 18-7-25
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DataSourceContextHolder.getDB();
        log.debug("[dynamic]change datasource to {}", dataSource);
        return dataSource;
    }


}
