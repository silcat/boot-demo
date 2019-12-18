package com.boot.demo.model.mq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class MQNotify<T> {
    private Long uid;
    private String creditRequestId;
    private String type;
    private T data;
    private String traceId;


    public MQNotify() {
    }

    public MQNotify(Long uid, String creditRequestId, String type, T data) {
        this.uid = uid;
        this.creditRequestId = creditRequestId;
        this.type = type;
        this.data = data;
    }
}
