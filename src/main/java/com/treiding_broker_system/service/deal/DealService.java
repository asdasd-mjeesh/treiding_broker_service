package com.treiding_broker_system.service.deal;

import com.treiding_broker_system.model.deal.Deal;

import java.util.List;

public interface DealService {
    Deal create(Deal deal);

    List<Deal> getAll();

    List<Deal> createAll(List<Deal> newDeals);
}
