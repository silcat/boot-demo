package com.boot.demo.model.mq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author sunlichuan
 * Created by sunlichuan on 18-10-19
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MQNotify<T> {
    private Long uid;
    private String creditRequestId;
    private String type;
    private T data;
    private String traceId;
}
