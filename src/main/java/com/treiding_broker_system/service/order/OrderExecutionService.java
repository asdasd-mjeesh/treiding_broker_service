package com.treiding_broker_system.service.order;

import com.treiding_broker_system.model.deal.Deal;
import com.treiding_broker_system.model.order.Order;

import java.util.List;

public interface OrderExecutionService {
    List<Deal> execute(Order targetOrder, List<Order> allOrders);
}
