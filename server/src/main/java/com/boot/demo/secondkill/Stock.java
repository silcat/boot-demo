package com.boot.demo.secondkill;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    private Integer id;

    private String name;

    private Integer count;

    private Integer sale;

    private Integer version;
}
