package com.treiding_broker_system.service.order;

import com.treiding_broker_system.model.order.Order;

import java.util.List;

public interface OrderActionFacade {
    Order createAndExecute(Order order);

    List<Order> getByUserId(Long userId);

    List<Order> getAll();
}
