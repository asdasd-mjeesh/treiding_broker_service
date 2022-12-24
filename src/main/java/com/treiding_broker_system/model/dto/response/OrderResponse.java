package com.treiding_broker_system.model.dto.response;

import com.treiding_broker_system.model.instrument.Instrument;
import com.treiding_broker_system.model.order.Status;
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
public class OrderResponse {
    private Long id;
    private Instrument instrument;
    private Integer count;
    private TargetAction action;
    private LocalDateTime expirationDate;
    private BigDecimal price;
    private Status status;
    private UserResponse owner;
}
