package com.treiding_broker_system.repository;

import com.treiding_broker_system.model.instrument.Instrument;
import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.OrderFilter;
import com.treiding_broker_system.model.order.Status;
import com.treiding_broker_system.model.order.TargetAction;
import com.treiding_broker_system.model.user.Role;
import com.treiding_broker_system.model.user.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

//    @PostConstruct
    private void init() {
        orders.add(Order.builder()
                        .id(5L)
                        .instrument(new Instrument(1L, "TEST"))
                        .expirationDate(LocalDateTime.now())
                        .action(TargetAction.BUY)
                        .initialCount(5)
                        .currentCount(5)
                        .price(BigDecimal.valueOf(13.37))
                        .status(Status.ACTIVE)
                        .owner(new User(5L, "TEST", "TEST", BigDecimal.ZERO, Role.USER, null))
                .build());
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
