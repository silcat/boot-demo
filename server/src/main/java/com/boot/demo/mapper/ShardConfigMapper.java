package com.boot.demo.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.demo.bean.ShardConfig;

import java.util.List;

public interface ShardConfigMapper extends BaseMapper<ShardConfig> {
    /**
     * selectByPrimaryKey
     * @param configKey
     * @return com.jay.model.ShardConfig
     */
    ShardConfig selectByPrimaryKey(String configKey);

    /**
     * selectAll
     * @param keys
     * @return java.util.List<com.jay.model.ShardConfig>
     */
    List<ShardConfig> selectByKey(List<String> keys);
}