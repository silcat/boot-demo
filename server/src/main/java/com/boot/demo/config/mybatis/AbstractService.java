package com.boot.demo.config.mybatis;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * <p>
 * AbstractService  Implement By MyBatis
 * <p>
 * 基于通用Mapper的MyBatis来实现Service接口的抽象Service
 */

public abstract class AbstractService<T> implements BaseService<T> {

    @Autowired
    protected Mapper<T> mapper;

    private Class<T> modelClass;
    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public void save(T model) {
        mapper.insertSelective(model);
    }
    @Override
    public void save(List<T> models) {
        mapper.insertList(models);
    }
    @Override
    public void deleteById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
    @Override
    public void deleteByIds(String ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    public void update(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }
    @Override
    public T findById(Integer id) {
        return (T) mapper.selectByPrimaryKey(id);
    }
    @Override
    public T findByCreditRequestId(String creditRequestId){
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField("creditRequestId");
            field.setAccessible(true);
            field.set(model, creditRequestId);
            return (T) mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new TooManyResultsException(e.getMessage(), e);
        }

    }

    @Override
    public T findBy(String property, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(property);
            field.setAccessible(true);
            field.set(model, value);
            return (T) mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new TooManyResultsException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }
    @Override
    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }
    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }


}