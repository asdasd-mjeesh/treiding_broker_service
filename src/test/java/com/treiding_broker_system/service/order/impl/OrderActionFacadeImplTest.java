package com.treiding_broker_system.service.order.impl;

import com.treiding_broker_system.model.instrument.Instrument;
import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.Status;
import com.treiding_broker_system.model.order.TargetAction;
import com.treiding_broker_system.model.user.User;
import com.treiding_broker_system.service.deal.DealService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  These are not functional tests.
 *  I've written them only to track the result by watching them through println
 * */
@SpringBootTest
class OrderActionFacadeImplTest {
    @Autowired
    private OrderActionFacadeImpl orderActionFacade;
    @Autowired
    private DealService dealService;

    @Test
    void createAndExecute() {
        var expDate = LocalDateTime.of(2025, 1, 4, 4, 4);

        var orders = List.of(
                Order.builder()
                        .owner(User.builder()
                                .id(9L)
                                .build())
                        .id(9L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .price(BigDecimal.TEN)
                        .initialCount(9)
                        .currentCount(9)
                        .status(Status.ACTIVE)
                        .expirationDate(expDate)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(2L)
                                .build())
                        .id(2L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .price(BigDecimal.TEN)
                        .initialCount(2)
                        .currentCount(2)
                        .status(Status.ACTIVE)
                        .expirationDate(expDate)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(3L)
                                .build())
                        .id(3L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .price(BigDecimal.TEN)
                        .initialCount(3)
                        .currentCount(3)
                        .status(Status.ACTIVE)
                        .expirationDate(expDate)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .build())
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .price(BigDecimal.TEN)
                        .action(TargetAction.SELL)
                        .initialCount(4)
                        .currentCount(4)
                        .status(Status.ACTIVE)
                        .expirationDate(expDate)
                        .build()
        );

        orders.forEach(order -> orderActionFacade.createAndExecute(order));

        var targetOrder = Order.builder()
                .owner(User.builder()
                        .id(1111L)
                        .build())
                .id(1L)
                .action(TargetAction.SELL)
                .instrument(Instrument.builder()
                        .id(43L)
                        .title("Meat")
                        .build())
                .price(BigDecimal.TEN)
                .initialCount(1)
                .currentCount(1)
                .status(Status.ACTIVE)
                .expirationDate(expDate)
                .build();

        orderActionFacade.createAndExecute(targetOrder);
        var deals = dealService.getAll();

        deals.forEach(deal -> System.out.println("TEST: " + deal));
    }
}