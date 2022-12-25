package com.treiding_broker_system.service.deal;

import com.treiding_broker_system.model.deal.Deal;
import com.treiding_broker_system.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealDatabaseService implements DealService {
    private final DealRepository dealRepository;

    @Override
    public Deal create(Deal deal) {
        return dealRepository.create(deal);
    }

    @Override
    public List<Deal> getAll() {
        return dealRepository.getAll();
    }

    @Override
    public List<Deal> createAll(List<Deal> newDeals) {
        return dealRepository.createAll(newDeals);
    }
}
