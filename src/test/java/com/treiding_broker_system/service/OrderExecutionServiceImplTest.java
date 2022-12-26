package com.treiding_broker_system.service;

import com.treiding_broker_system.model.instrument.Instrument;
import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.Status;
import com.treiding_broker_system.model.order.TargetAction;
import com.treiding_broker_system.model.user.User;
import com.treiding_broker_system.service.order.impl.OrderExecutionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

/**
 *  These are not functional tests.
 *  I've written them only to track the result by watching them through println
 * */
@SpringBootTest
class OrderExecutionServiceImplTest {
    @Autowired
    private OrderExecutionServiceImpl orderExecutionServiceImpl;

    @Test
    void execute() {
        var orders = List.of(
                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .balance(BigDecimal.valueOf(1000))
                                .availableBalance(BigDecimal.valueOf(1000))
                                .build())
                        .id(1L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(11L)
                                .title("Meat")
                                .build())
                        .initialCount(5)
                        .currentCount(5)
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .balance(BigDecimal.valueOf(1000))
                                .availableBalance(BigDecimal.valueOf(1000))
                                .build())
                        .id(2L)
                        .action(TargetAction.SELL)
                        .instrument(Instrument.builder()
                                .id(2L)
                                .title("Meat")
                                .build())
                        .initialCount(9)
                        .currentCount(9)
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .balance(BigDecimal.valueOf(1000))
                                .availableBalance(BigDecimal.valueOf(1000))
                                .build())
                        .id(3L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(5)
                        .currentCount(5)
                        .status(Status.ACTIVE)
                        .build()
        );

        var targetOrder = Order.builder()
                .owner(User.builder()
                        .id(4L)
                        .balance(BigDecimal.valueOf(1000))
                        .availableBalance(BigDecimal.valueOf(1000))
                        .build())
                .instrument(Instrument.builder()
                        .id(1337L)
                        .title("Meat")
                        .build())
                .action(TargetAction.SELL)
                .initialCount(10)
                .currentCount(10)
                .status(Status.ACTIVE)
                .build();

        var result = orderExecutionServiceImpl.execute(targetOrder, orders);
        result.forEach(System.out::println);
    }

    @Test
    void execute2() {
        var orders = List.of(
                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .balance(BigDecimal.valueOf(1000))
                                .availableBalance(BigDecimal.valueOf(1000))
                                .build())
                        .id(1L)
                        .action(TargetAction.SELL)
                        .instrument(Instrument.builder()
                                .id(11L)
                                .title("Meat")
                                .build())
                        .initialCount(1)
                        .currentCount(1)
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .balance(BigDecimal.valueOf(1000))
                                .availableBalance(BigDecimal.valueOf(1000))
                                .build())
                        .id(3L)
                        .action(TargetAction.SELL)
                        .instrument(Instrument.builder()
                                .id(2L)
                                .title("Meat")
                                .build())
                        .initialCount(3)
                        .currentCount(3)
                        .status(Status.ACTIVE)
                        .build()
        );

        var targetOrder = Order.builder()
                .owner(User.builder()
                        .id(4L)
                        .balance(BigDecimal.valueOf(1000))
                        .availableBalance(BigDecimal.valueOf(1000))
                        .build())
                .instrument(Instrument.builder()
                        .id(1337L)
                        .title("Meat")
                        .build())
                .action(TargetAction.BUY)
                .initialCount(4)
                .currentCount(4)
                .status(Status.ACTIVE)
                .build();

        var result = orderExecutionServiceImpl.execute(targetOrder, orders);
        result.forEach(System.out::println);
    }

    @Test
    void execute3() {
        var orders = List.of(
                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .balance(BigDecimal.valueOf(1000))
                                .build())
                        .id(9L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(9)
                        .currentCount(9)
                        .price(BigDecimal.valueOf(55))
                        .currentPrice(BigDecimal.valueOf(55))
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .balance(BigDecimal.valueOf(1000))
                                .build())
                        .id(2L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(2)
                        .currentCount(2)
                        .price(BigDecimal.valueOf(30))
                        .currentPrice(BigDecimal.valueOf(30))
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .balance(BigDecimal.valueOf(1000))
                                .build())
                        .id(3L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(3)
                        .currentCount(3)
                        .price(BigDecimal.valueOf(25))
                        .currentPrice(BigDecimal.valueOf(25))
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .balance(BigDecimal.valueOf(1000))
                                .build())
                        .id(1L)
                        .action(TargetAction.SELL)
                        .instrument(Instrument.builder()
                                .id(12L)
                                .title("Meat")
                                .build())
                        .initialCount(1)
                        .currentCount(1)
                        .price(BigDecimal.valueOf(5))
                        .currentPrice(BigDecimal.valueOf(5))
                        .status(Status.ACTIVE)
                        .build()
        );

        var targetOrder = Order.builder()
                .owner(User.builder()
                        .id(4L)
                        .balance(BigDecimal.valueOf(1000))
                        .build())
                .instrument(Instrument.builder()
                        .id(1337L)
                        .title("Pork meat")
                        .build())
                .action(TargetAction.SELL)
                .initialCount(4)
                .currentCount(4)
                .price(BigDecimal.valueOf(50))
                .currentPrice(BigDecimal.valueOf(50))
                .status(Status.ACTIVE)
                .build();

        var result = orderExecutionServiceImpl.execute(targetOrder, orders);
        result.forEach(System.out::println);
    }

    @Test
    void execute11() {
        var orders = List.of(
                Order.builder()
                        .owner(User.builder()
                                .balance(BigDecimal.valueOf(1000))
                                .id(4L)
                                .build())
                        .id(9L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(9)
                        .currentCount(9)
                        .price(BigDecimal.valueOf(60))
                        .currentPrice(BigDecimal.valueOf(60))
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .balance(BigDecimal.valueOf(1000))
                                .id(4L)
                                .build())
                        .id(2L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(2)
                        .currentCount(2)
                        .price(BigDecimal.valueOf(30))
                        .currentPrice(BigDecimal.valueOf(30))
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .balance(BigDecimal.valueOf(1000))
                                .id(4L)
                                .build())
                        .id(3L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(3)
                        .currentCount(3)
                        .price(BigDecimal.valueOf(25))
                        .currentPrice(BigDecimal.valueOf(25))
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .balance(BigDecimal.valueOf(1000))
                                .id(4L)
                                .build())
                        .instrument(Instrument.builder()
                                .id(1337L)
                                .title("Pork meat")
                                .build())
                        .action(TargetAction.SELL)
                        .price(BigDecimal.valueOf(50))
                        .currentPrice(BigDecimal.valueOf(50))
                        .initialCount(4)
                        .currentCount(4)
                        .status(Status.ACTIVE)
                        .build()
        );


        var targetOrder = Order.builder()
                .owner(User.builder()
                        .id(4L)
                        .balance(BigDecimal.valueOf(1000))
                        .build())
                .id(1L)
                .action(TargetAction.SELL)
                .instrument(Instrument.builder()
                        .id(12L)
                        .title("Meat")
                        .build())
                .initialCount(1)
                .currentCount(1)
                .price(BigDecimal.valueOf(5))
                .currentPrice(BigDecimal.valueOf(5))
                .status(Status.ACTIVE)
                .build();

        var result = orderExecutionServiceImpl.execute(targetOrder, orders);
        result.forEach(System.out::println);
    }

    @Test
    void execute4() {
        var orders = List.of(
                Order.builder()
                        .id(1L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(1L)
                                .title("Meat")
                                .build())
                        .initialCount(1)
                        .currentCount(1)
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .id(6L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(6L)
                                .title("Meat")
                                .build())
                        .initialCount(6)
                        .currentCount(6)
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .id(2L)
                        .action(TargetAction.SELL)
                        .instrument(Instrument.builder()
                                .id(2L)
                                .title("Meat")
                                .build())
                        .initialCount(2)
                        .currentCount(2)
                        .status(Status.ACTIVE)
                        .build()
        );

        var targetOrder = Order.builder()
                .id(5L)
                .instrument(Instrument.builder()
                        .id(5L)
                        .title("Pork meat")
                        .build())
                .action(TargetAction.SELL)
                .initialCount(5)
                .currentCount(5)
                .status(Status.ACTIVE)
                .build();

        var result = orderExecutionServiceImpl.execute(targetOrder, orders);
        result.forEach(System.out::println);
    }

    @Test
    void execute5() {
        var orders = List.of(
                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .build())
                        .id(9L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(1L)
                                .title("Meat")
                                .build())
                        .initialCount(1)
                        .currentCount(1)
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .build())
                        .id(6L)
                        .action(TargetAction.SELL)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(6)
                        .currentCount(6)
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .build())
                        .id(3L)
                        .action(TargetAction.SELL)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(2)
                        .currentCount(2)
                        .status(Status.ACTIVE)
                        .build()
        );

        var targetOrder = Order.builder()
                .owner(User.builder()
                        .id(4L)
                        .build())
                .instrument(Instrument.builder()
                        .id(1337L)
                        .title("Pork meat")
                        .build())
                .action(TargetAction.BUY)
                .initialCount(5)
                .currentCount(5)
                .status(Status.ACTIVE)
                .build();

        var result = orderExecutionServiceImpl.execute(targetOrder, orders);
        result.forEach(System.out::println);
    }

    @Test
    void execute6() {
        var orders = List.of(
                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .build())
                        .id(9L)
                        .action(TargetAction.SELL)
                        .instrument(Instrument.builder()
                                .id(1L)
                                .title("Meat")
                                .build())
                        .initialCount(1)
                        .currentCount(1)
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .build())
                        .id(6L)
                        .action(TargetAction.SELL)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(6)
                        .currentCount(6)
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .owner(User.builder()
                                .id(4L)
                                .build())
                        .id(3L)
                        .action(TargetAction.SELL)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(2)
                        .currentCount(2)
                        .status(Status.ACTIVE)
                        .build()
        );

        var targetOrder = Order.builder()
                .owner(User.builder()
                        .id(4L)
                        .build())
                .instrument(Instrument.builder()
                        .id(1337L)
                        .title("Pork meat")
                        .build())
                .action(TargetAction.BUY)
                .initialCount(5)
                .currentCount(5)
                .status(Status.ACTIVE)
                .build();

        var result = orderExecutionServiceImpl.execute(targetOrder, orders);
        result.forEach(System.out::println);
        System.out.println(result.size());
        System.out.println(result);
    }
}