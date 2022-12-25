package com.treiding_broker_system.model.dto.response;

import com.treiding_broker_system.model.instrument.Instrument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealResponse {
    private Long id;
    private String seller;
    private String buyer;
    private Instrument instrument;
    private Integer count;
    private BigDecimal price;
}
