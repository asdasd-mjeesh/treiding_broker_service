package com.treiding_broker_system.service.order.impl;

import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.OrderFilter;
import com.treiding_broker_system.model.order.Status;
import com.treiding_broker_system.service.deal.DealService;
import com.treiding_broker_system.service.order.OrderActionFacade;
import com.treiding_broker_system.service.order.OrderExecutionService;
import com.treiding_broker_system.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderActionFacadeImpl implements OrderActionFacade {
    private final OrderExecutionService orderExecutionService;
    private final OrderService orderService;
    private final DealService dealService;

    @Override
    //@Transactional
    public Order createAndExecute(Order order) {
        this.updateOrdersState();

        orderService.create(order);
        var relatedOrders = orderService.getAllRelatedOrders(
                new OrderFilter(order.getInstrument(), order.getOwner().getId(), LocalDateTime.now()));

        var deals = orderExecutionService.execute(order, relatedOrders);

        if (!deals.isEmpty()) {
            dealService.createAll(deals);
            orderService.update(order);
            orderService.updateAll(relatedOrders);
        }
        return order;
    }

    @Override
    public List<Order> getByUserId(Long userId) {
        this.updateOrdersState();
        return orderService.getByUserId(userId);
    }

    @Override
    public List<Order> getAll() {
        this.updateOrdersState();
        return orderService.getAll();
    }

    private void updateOrdersState() {
        var allOrders = orderService.getAll();
        allOrders.forEach(order -> {
            if (order.getExpirationDate().isBefore(LocalDateTime.now())) {
                order.setStatus(Status.EXPIRED);
            }
        });
    }
}
