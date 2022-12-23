package com.treiding_broker_system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private String item;
    private Integer count;
    private TargetAction action;
}
