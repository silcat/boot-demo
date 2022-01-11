package com.boot.demo.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("shard_config")
public class ShardConfig {
    /**
     * 配置键
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId
    private String configKey;

    /**
     * 配置值
     */
    private String configValue;


}