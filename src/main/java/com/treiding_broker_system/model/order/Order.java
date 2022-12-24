package com.treiding_broker_system.model.order;

import com.treiding_broker_system.model.instrument.Instrument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Instrument instrument;
    private Integer initialCount;
    private Integer currentCount;
    private TargetAction action;
    private LocalDateTime expirationDate;
    private BigDecimal price;
    private Status status;
}
