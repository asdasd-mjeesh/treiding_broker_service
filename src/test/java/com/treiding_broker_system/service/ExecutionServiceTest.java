package com.treiding_broker_system.service;

import com.treiding_broker_system.model.Order;
import com.treiding_broker_system.model.TargetAction;
import com.treiding_broker_system.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExecutionServiceTest {
    @Autowired
    private ExecutionService executionService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void execute() {
        var orders = orderRepository.getAll();

        var targetOrder = Order.builder()
                .item("Pork meat")
                .action(TargetAction.SELL)
                .count(10)
                .build();
        var result = executionService.execute(targetOrder, orders, new ArrayList<>());
        System.out.println(result);
    }
}