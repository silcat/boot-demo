package com.boot.demo.config.mybatis;

import com.boot.demo.annotation.DataSourceContextHolder;
import com.boot.demo.annotation.DataSourceTarget;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by sunlichuan on 18-7-25
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.boot.demo.annotation.DataSourceTarget)")
    public void authAnnotation() {
    }


    @Before("authAnnotation()")
    public void beforeSwitchDS(JoinPoint point){
        // 获取annotation
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        DataSourceTarget auth = method.getAnnotation(DataSourceTarget.class);
        // 取出注解中的数据源名
        String dataSource = auth.value();
        // 切换数据源
        DataSourceContextHolder.setDB(dataSource);
    }

    @After("authAnnotation()")
    public void afterSwitchDS(JoinPoint point){
        DataSourceContextHolder.clearDB();
    }


}
