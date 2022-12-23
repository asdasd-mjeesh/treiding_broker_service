package com.treiding_broker_system.repository;

import com.treiding_broker_system.model.Order;
import com.treiding_broker_system.model.TargetAction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private final List<Order> orders;

    {
        orders = new ArrayList<>(List.of(
                Order.builder()
                        .id(1L)
                        .action(TargetAction.BUY)
                        .item("Pork meat")
                        .count(5)
                        .build(),
                Order.builder()
                        .id(2L)
                        .action(TargetAction.SELL)
                        .item("Pork meat")
                        .count(9)
                        .build(),
                Order.builder()
                        .id(3L)
                        .action(TargetAction.BUY)
                        .item("Pork meat")
                        .count(5)
                        .build()
        ));
    }

    public List<Order> getAll() {
        return this.orders;
    }
}
