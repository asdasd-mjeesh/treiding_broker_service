package com.treiding_broker_system.repository;

import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.OrderFilter;
import com.treiding_broker_system.model.order.Status;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    private static Long ORDERS_COUNT = 0L;
    private final List<Order> orders = new ArrayList<>();

    public Order create(Order order) {
        order.setId(++ORDERS_COUNT);
        orders.add(order);
        return order;
    }

    public List<Order> getByUserId(Long userId) {
        return orders.stream()
                .filter(order -> order.getOwner().getId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Order> getAllRelatedOrders(OrderFilter filter) {
        return orders.stream()
                .filter(order ->
                        !order.getOwner().getId().equals(filter.getResearcherId()) &&
                        order.getInstrument().equals(filter.getTargetInstrument()) &&
                        order.getExpirationDate().isAfter(filter.getMinDateTimeLimitation()) &&
                        order.getStatus().equals(Status.ACTIVE))
                .collect(Collectors.toList());
    }

    public List<Order> getAll() {
        return this.orders;
    }

    public void update(Order order) {
        // It's unnecessary action because I change value by the link,
        // but anyway I've created this method for logic demonstration
        // and potential future migration data storing to the database
    }

    public void updateAll(List<Order> ordersForUpdating) {
        // It's unnecessary action because I change value by the link,
        // but anyway I've created this method for logic demonstration
        // and potential future migration data storing to the database
    }
}
