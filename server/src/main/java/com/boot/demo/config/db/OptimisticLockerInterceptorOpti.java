package com.boot.demo.config.db;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.dao.OptimisticLockingFailureException;

/**
 * @author wangyaozhou
 * 由于原生的乐观锁更新失败时不进行异常抛出 所以扩展重写
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class OptimisticLockerInterceptorOpti extends OptimisticLockerInterceptor {
    /**
     * 继承判断受影响行数
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object rows = super.intercept(invocation);
        if (rows instanceof Integer && (Integer) rows == 0) {
            throw new OptimisticLockingFailureException("乐观锁异常！");
        }else {
            return rows;
        }
    }

}

