package com.treiding_broker_system.repository;

import com.treiding_broker_system.model.deal.Deal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DealRepository {
    private static Long DEALS_COUNT = 0L;
    private final List<Deal> deals = new ArrayList<>();
    private final OrderRepository orderRepository;

    @PostConstruct
    private void init() {
        deals.add(Deal.builder()
                        .id(213L)
                        .buyerOrder(orderRepository.getAll().get(0))
                        .sellerOrder(orderRepository.getAll().get(0))
                .build());
    }

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
