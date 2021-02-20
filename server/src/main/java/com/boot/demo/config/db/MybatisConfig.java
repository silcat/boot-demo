package com.boot.demo.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean
    public OptimisticLockerInterceptorOpti optimisticLockerInterceptorOpti() {
        return new OptimisticLockerInterceptorOpti();
    }
}
