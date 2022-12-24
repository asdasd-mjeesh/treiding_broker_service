package com.treiding_broker_system.repository;

import com.treiding_broker_system.model.order.Deal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DealRepository {
    private static Long DEALS_COUNT = 0L;
    private final List<Deal> deals = new ArrayList<>();

    public Deal create(Deal deal) {
        deal.setId(++DEALS_COUNT);
        deals.add(deal);
        return deal;
    }

    public List<Deal> createAll(List<Deal> newDeals) {
        newDeals.forEach(newDeal -> {
            newDeal.setId(++DEALS_COUNT);
            deals.add(newDeal);
        });
        return newDeals;
    }

    public List<Deal> getAll() {
        return this.deals;
    }
}
