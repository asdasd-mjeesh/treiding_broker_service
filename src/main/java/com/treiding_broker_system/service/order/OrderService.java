package com.treiding_broker_system.service.order;

import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.OrderFilter;

import java.util.List;

public interface OrderService {
    Order create(Order order);

    List<Order> getByUserId(Long userId);

    List<Order> getAllRelatedOrders(OrderFilter filter);

    List<Order> getAll();

    void update(Order order);

    void updateAll(List<Order> ordersForUpdating);
}
