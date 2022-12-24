package com.treiding_broker_system.repository;

import com.treiding_broker_system.model.instrument.Instrument;
import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.Status;
import com.treiding_broker_system.model.order.TargetAction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private final List<Order> orders;

    {
        orders = new ArrayList<>(List.of(





        ));
    }

    public List<Order> getAll() {
        return this.orders;
    }
}
