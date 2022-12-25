package com.treiding_broker_system.model.deal;

import com.treiding_broker_system.model.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deal {
    private Long id;
    private Order buyerOrder;
    private Order sellerOrder;
}
