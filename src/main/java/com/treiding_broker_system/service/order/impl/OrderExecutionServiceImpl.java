package com.treiding_broker_system.service.order.impl;

import com.treiding_broker_system.model.order.Deal;
import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.Status;
import com.treiding_broker_system.model.order.TargetAction;
import com.treiding_broker_system.service.order.OrderExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderExecutionServiceImpl implements OrderExecutionService {
    private static Long deals = 1L;

    /**
     * Basically I've created logic by way where we are executing
     * the oldest existed orders first of the all independents of required item count.
     * I guess will be better execute and done old orders before they expired.
     */
    public List<Deal> execute(Order targetOrder, List<Order> allOrders) {
        for (int i = 0; i < allOrders.size(); i++) {
            var deals = this.execute(targetOrder, allOrders, new ArrayList<>());
            if (!deals.isEmpty()) {
                this.setEventualStatus(deals, allOrders);
                return deals;
            }
            this.resetDetailsBack(allOrders, targetOrder);
        }
        return new ArrayList<>();
    }

    private void setEventualStatus(List<Deal> deals, List<Order> orders) {
        deals.forEach(deal -> {
            deal.getBuyerOrder().setStatus(Status.DONE);
            deal.getSellerOrder().setStatus(Status.DONE);
        });

        orders.stream()
                .filter(order -> order.getStatus().equals(Status.CAPTURED_BY_PROCESS))
                .filter(order -> order.getStatus().equals(Status.NOT_CONVENIENT))
                .forEach(order -> order.setStatus(Status.ACTIVE));
    }

    private void resetDetailsBack(List<Order> allOrders, Order targetOrder) {
        targetOrder.setStatus(Status.ACTIVE);
        targetOrder.setCurrentCount(targetOrder.getInitialCount());
        allOrders.stream()
                .filter(order -> order.getStatus().equals(Status.CAPTURED_BY_PROCESS))
                .forEach(order -> {
                    order.setStatus(Status.ACTIVE);
                    order.setCurrentCount(order.getInitialCount());
                });
    }

    private List<Deal> execute(Order targetOrder, List<Order> allOrders, List<Deal> allDeals) {
        // maybe will be better transmit to here two separate lists instead filtering this one
        var oppositOrderOptional = allOrders.stream()
                .filter(oppositOrder -> !oppositOrder.getAction().equals(targetOrder.getAction()))
                .filter(oppositOrder -> !oppositOrder.getStatus().equals(Status.CAPTURED_BY_PROCESS))
                .filter(oppositOrder -> !oppositOrder.getStatus().equals(Status.NOT_CONVENIENT))
                .findAny();

        if (oppositOrderOptional.isEmpty()) {
            if (allOrders.contains(targetOrder)) {
                targetOrder.setStatus(Status.NOT_CONVENIENT);
            }
            return new ArrayList<>();
        }
        var oppositOrder = oppositOrderOptional.get();

        if (targetOrder.getCurrentCount().equals(oppositOrder.getCurrentCount())) {
            targetOrder.setCurrentCount(0);
            oppositOrder.setCurrentCount(0);
            allDeals.add(this.createDeal(targetOrder, oppositOrder));
            return allDeals;
        }

        if (targetOrder.getCurrentCount().compareTo(oppositOrder.getCurrentCount()) < 0) {
            oppositOrder.setCurrentCount(oppositOrder.getCurrentCount() - targetOrder.getCurrentCount());
            targetOrder.setCurrentCount(0);
            allDeals.add(this.createDeal(targetOrder, oppositOrder));
            return this.execute(oppositOrder, allOrders, allDeals);
        }

        if (targetOrder.getCurrentCount().compareTo(oppositOrder.getCurrentCount()) > 0) {
            targetOrder.setCurrentCount(targetOrder.getCurrentCount() - oppositOrder.getCurrentCount());
            oppositOrder.setCurrentCount(0);
            allDeals.add(this.createDeal(oppositOrder, targetOrder));
            return this.execute(targetOrder, allOrders, allDeals);
        }

        throw new RuntimeException();
    }

    protected Deal createDeal(Order targetOrder, Order oppositOrder) {
        targetOrder.setStatus(Status.CAPTURED_BY_PROCESS);
        oppositOrder.setStatus(Status.CAPTURED_BY_PROCESS);
        if (targetOrder.getAction().equals(TargetAction.BUY)) {
            return new Deal(deals++, targetOrder, oppositOrder);
        }
        return new Deal(deals++, oppositOrder, targetOrder);
    }
}