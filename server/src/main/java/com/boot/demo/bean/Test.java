package com.boot.demo.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("test")
public class Test {
    /**
     * 地区表自增id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long uid;
    @Column(name = "standard_code")
    private String testMessage;
}