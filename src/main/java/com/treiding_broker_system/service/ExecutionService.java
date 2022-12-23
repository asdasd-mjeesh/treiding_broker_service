package com.treiding_broker_system.service;

import com.treiding_broker_system.model.Deal;
import com.treiding_broker_system.model.Order;
import com.treiding_broker_system.model.TargetAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExecutionService {
    private static Long deals = 1L;

    /**
     *   Basically I've created logic by way where we are executing
     *   the oldest existed orders first of the all independents of required item count.
     *   I guess will be better execute and done old orders before they expired.
     * */
    public List<Deal> execute(Order targetOrder, List<Order> allOrders, List<Deal> allDeals) {
        var oppositOrders = allOrders.stream()
                .filter(oppositOrder -> !oppositOrder.getAction().equals(targetOrder.getAction()))
                .collect(Collectors.toList());

        for (Order oppositOrder : oppositOrders) {
            if (targetOrder.getCount().equals(oppositOrder.getCount())) {
                oppositOrder.setCount(oppositOrder.getCount() - targetOrder.getCount());
                allDeals.add(this.createDeal(targetOrder, oppositOrder));
                return allDeals;
            }

            if (targetOrder.getCount().compareTo(oppositOrder.getCount()) < 0) {
                oppositOrder.setCount(oppositOrder.getCount() - targetOrder.getCount());
                allDeals.add(this.createDeal(targetOrder, oppositOrder));
                return this.execute(oppositOrder, allOrders, allDeals);
            }

            if (targetOrder.getCount().compareTo(oppositOrder.getCount()) > 0) {
                targetOrder.setCount(targetOrder.getCount() - oppositOrder.getCount());
                allDeals.add(this.createDeal(oppositOrder, targetOrder));
                return this.execute(targetOrder, allOrders, allDeals);
            }
        }
        return new ArrayList<>();
    }

    protected Deal createDeal(Order targetOrder, Order oppositOrder) {
        if (targetOrder.getAction().equals(TargetAction.BUY)) {
            return new Deal(deals++, targetOrder, oppositOrder);
        }
        return new Deal(deals++, oppositOrder, targetOrder);
    }
}
