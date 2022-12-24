package com.treiding_broker_system.service;

import com.treiding_broker_system.model.instrument.Instrument;
import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.Status;
import com.treiding_broker_system.model.order.TargetAction;
import com.treiding_broker_system.service.order.impl.OrderExecutionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
                        .id(9L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(9)
                        .currentCount(9)
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .id(2L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(2)
                        .currentCount(2)
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .id(3L)
                        .action(TargetAction.BUY)
                        .instrument(Instrument.builder()
                                .id(43L)
                                .title("Meat")
                                .build())
                        .initialCount(3)
                        .currentCount(3)
                        .status(Status.ACTIVE)
                        .build(),

                Order.builder()
                        .id(1L)
                        .action(TargetAction.SELL)
                        .instrument(Instrument.builder()
                                .id(12L)
                                .title("Meat")
                                .build())
                        .initialCount(1)
                        .currentCount(1)
                        .status(Status.ACTIVE)
                        .build()
        );

        var targetOrder = Order.builder()
                .instrument(Instrument.builder()
                        .id(1337L)
                        .title("Pork meat")
                        .build())
                .action(TargetAction.SELL)
                .initialCount(4)
                .currentCount(4)
                .status(Status.ACTIVE)
                .build();

        var result = orderExecutionServiceImpl.execute(targetOrder, orders);
        result.forEach(System.out::println);
    }

    @Test
    void execute4() {
        var orders = List.of(
                Order.builder()
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
                        .id(3L)
                        .action(TargetAction.BUY)
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
    void execute5() {
        var orders = List.of(
                Order.builder()
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