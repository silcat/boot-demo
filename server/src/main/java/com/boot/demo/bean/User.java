package com.boot.demo.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

/**
 * user表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {

    @TableId
    private Long id;
    private Integer age;
    @Column(name = "user_id")
    private long userId;


}