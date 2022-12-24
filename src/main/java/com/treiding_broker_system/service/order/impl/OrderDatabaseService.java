package com.treiding_broker_system.service.order.impl;

import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.OrderFilter;
import com.treiding_broker_system.repository.OrderRepository;
import com.treiding_broker_system.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDatabaseService implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        return orderRepository.create(order);
    }

    @Override
    public List<Order> getByUserId(Long userId) {
        return orderRepository.getByUserId(userId);
    }

    @Override
    public List<Order> getAllRelatedOrders(OrderFilter filter) {
        return orderRepository.getAllRelatedOrders(filter);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public void update(Order order) {
        orderRepository.update(order);
    }

    @Override
    public void updateAll(List<Order> ordersForUpdating) {
        orderRepository.updateAll(ordersForUpdating);
    }
}
