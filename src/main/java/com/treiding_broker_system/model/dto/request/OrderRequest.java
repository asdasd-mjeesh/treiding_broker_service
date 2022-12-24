package com.treiding_broker_system.model.dto.request;

import com.treiding_broker_system.model.instrument.Instrument;
import com.treiding_broker_system.model.order.TargetAction;
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
public class OrderRequest {
    private Instrument targetInstrument;
    private Integer count;
    private BigDecimal price;
    private TargetAction action;
    private UserRequest owner;
    private LocalDateTime expirationDate;
}
