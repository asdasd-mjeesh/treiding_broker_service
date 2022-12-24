package com.treiding_broker_system.model.order;

import com.treiding_broker_system.model.instrument.Instrument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFilter {
    private Instrument targetInstrument;
    private Long researcherId;
}
