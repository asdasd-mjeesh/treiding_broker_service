package com.treiding_broker_system.service.order.impl;

import com.treiding_broker_system.model.deal.Deal;
import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.Status;
import com.treiding_broker_system.model.order.TargetAction;
import com.treiding_broker_system.service.order.OrderExecutionService;
import com.treiding_broker_system.service.user.impl.UserBalanceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderExecutionServiceImpl implements OrderExecutionService {
    private static Long DEALS_COUNT = 1L;
    private final UserBalanceServiceImpl userBalanceService;

    public List<Deal> execute(Order targetOrder, List<Order> allOrders) {
        if (targetOrder.getAction().equals(TargetAction.BUY)) {
            userBalanceService.reduceAvailableBalance(targetOrder.getOwner(), targetOrder.getPrice());
        }

        for (int i = 0; i < allOrders.size(); i++) {
            var deals = this.execute(targetOrder, allOrders, new ArrayList<>());
            if (!deals.isEmpty()) {
                this.changeObjectsStatsAfterMadeTheDeal(deals, allOrders);
                return deals;
            }
            this.resetDetailsBack(allOrders, targetOrder);
        }
        this.resetNotConvenientOrdersBack(allOrders);
        return new ArrayList<>();
    }

    private List<Deal> execute(Order targetOrder, List<Order> allOrders, List<Deal> allDeals) {
        var oppositOrderOptional = this.getOppositOrder(allOrders, targetOrder);
        if (oppositOrderOptional.isEmpty() || !this.checkAbilityToBuy(targetOrder, oppositOrderOptional.get())) {
            if (allOrders.contains(targetOrder)) {
                targetOrder.setStatus(Status.NOT_CONVENIENT);
            }
            return new ArrayList<>();
        }
        var oppositOrder = oppositOrderOptional.get();

        // Probably three statements below will be better if combine them through else-if constructions,
        // but in that way code will be more difficult to read
        if (targetOrder.getCurrentCount().equals(oppositOrder.getCurrentCount())) {
            targetOrder.setCurrentCount(0);
            oppositOrder.setCurrentCount(0);
            this.setCurrentPrice(targetOrder, oppositOrder);
            allDeals.add(this.createDeal(targetOrder, oppositOrder));
            return allDeals;
        }

        if (targetOrder.getCurrentCount().compareTo(oppositOrder.getCurrentCount()) < 0) {
            oppositOrder.setCurrentCount(oppositOrder.getCurrentCount() - targetOrder.getCurrentCount());
            targetOrder.setCurrentCount(0);
            this.setCurrentPrice(targetOrder, oppositOrder);
            allDeals.add(this.createDeal(targetOrder, oppositOrder));
            return this.execute(oppositOrder, allOrders, allDeals);
        }

        if (targetOrder.getCurrentCount().compareTo(oppositOrder.getCurrentCount()) > 0) {
            targetOrder.setCurrentCount(targetOrder.getCurrentCount() - oppositOrder.getCurrentCount());
            oppositOrder.setCurrentCount(0);
            this.setCurrentPrice(targetOrder, oppositOrder);
            allDeals.add(this.createDeal(oppositOrder, targetOrder));
            return this.execute(targetOrder, allOrders, allDeals);
        }

        throw new RuntimeException("Something wrong with algorithm logic. We need to fix that");
    }

    private void setCurrentPrice(Order order1, Order order2) {
        var order1Price = order1.getCurrentPrice();
        var order2Price = order2.getCurrentPrice();
        order1.setCurrentPrice(order1Price.subtract(order2Price));
        order2.setCurrentPrice(order2Price.subtract(order1Price));
    }

    private boolean checkAbilityToBuy(Order order1, Order order2) {
        if (order1.getAction().equals(TargetAction.BUY)) {
            return order1.getCurrentPrice().compareTo(order2.getCurrentPrice()) >= 0 ||
                   !order1.getCurrentCount().equals(order2.getCurrentCount());
        }
        return order2.getCurrentPrice().compareTo(order1.getCurrentPrice()) >= 0 ||
               !order2.getCurrentCount().equals(order1.getCurrentCount());
    }

    private Optional<Order> getOppositOrder(List<Order> allOrders, Order targetOrder) {
        return allOrders.stream()
                .filter(oppositOrder ->
                        !oppositOrder.getAction().equals(targetOrder.getAction()) &&
                        !oppositOrder.getStatus().equals(Status.CAPTURED_BY_PROCESS) &&
                        !oppositOrder.getStatus().equals(Status.NOT_CONVENIENT))
                .findAny();
    }

    private Deal createDeal(Order targetOrder, Order oppositOrder) {
        targetOrder.setStatus(Status.CAPTURED_BY_PROCESS);
        oppositOrder.setStatus(Status.CAPTURED_BY_PROCESS);
        if (targetOrder.getAction().equals(TargetAction.BUY)) {
            return new Deal(DEALS_COUNT++, targetOrder, oppositOrder, targetOrder.getPrice());
        }
        return new Deal(DEALS_COUNT++, oppositOrder, targetOrder, oppositOrder.getPrice());
    }

    private void changeObjectsStatsAfterMadeTheDeal(List<Deal> deals, List<Order> allOrders) {
        this.setNewUserBalance(deals);
        this.resetNotConvenientOrdersBack(allOrders);
        this.setEventualStatus(deals, allOrders);
    }

    private void setNewUserBalance(List<Deal> deals) {
        deals.forEach(deal -> {
            var seller = deal.getSellerOrder().getOwner();
            var buyer = deal.getBuyerOrder().getOwner();
            seller.setBalance(seller.getBalance().add(deal.getSellerOrder().getPrice()));
            buyer.setBalance(buyer.getBalance().subtract(deal.getPrice()));
            buyer.setAvailableBalance(buyer.getBalance());
        });
    }

    private void setEventualStatus(List<Deal> deals, List<Order> orders) {
        deals.forEach(deal -> {
            deal.getBuyerOrder().setStatus(Status.DONE);
            deal.getSellerOrder().setStatus(Status.DONE);
        });

        orders.stream()
                .filter(order -> order.getStatus().equals(Status.CAPTURED_BY_PROCESS) ||
                                 order.getStatus().equals(Status.NOT_CONVENIENT))
                .forEach(order -> order.setStatus(Status.ACTIVE));
    }

    // methods below are for setting initial parameters to the orders.
    // I need it because I use that same objects by link-value which I got from repository(there are just lists with objects, not DB),
    // and I don't map that objects to some special models dto for specific execution in this algorithm-class,
    // instead that I'm just acting with initial object directly through changing certain attributes, such as status, currentPrice and currentCount
    // so if in the execution process values would be changed, it means that initial repository's values will be changed too,
    // and setting them back is necessary.

    private void resetDetailsBack(List<Order> allOrders, Order targetOrder) {
        targetOrder.setStatus(Status.ACTIVE);
        targetOrder.setCurrentCount(targetOrder.getInitialCount());
        targetOrder.setCurrentPrice(targetOrder.getPrice());

        allOrders.forEach(order -> {
            if (order.getStatus().equals(Status.CAPTURED_BY_PROCESS)) {
                order.setStatus(Status.ACTIVE);
            }
            order.setCurrentPrice(order.getPrice());
            order.setCurrentCount(order.getInitialCount());
        });
    }

    private void resetNotConvenientOrdersBack(List<Order> allOrders) {
        allOrders.stream()
                .filter(order -> order.getStatus().equals(Status.NOT_CONVENIENT))
                .forEach(order -> order.setStatus(Status.ACTIVE));
    }
}
