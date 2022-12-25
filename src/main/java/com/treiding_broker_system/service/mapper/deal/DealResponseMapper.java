package com.treiding_broker_system.service.mapper.deal;

import com.treiding_broker_system.model.deal.Deal;
import com.treiding_broker_system.model.dto.response.DealResponse;
import com.treiding_broker_system.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealResponseMapper implements Mapper<DealResponse, Deal> {

    @Override
    public DealResponse map(Deal from) {
        return DealResponse.builder()
                .id(from.getId())
                .seller(from.getSellerOrder().getOwner().getUsername())
                .buyer(from.getBuyerOrder().getOwner().getUsername())
                .instrument(from.getBuyerOrder().getInstrument())
                .count(from.getBuyerOrder().getInitialCount())
                .price(from.getBuyerOrder().getPrice())
                .build();
    }

    @Override
    public List<DealResponse> map(List<Deal> from) {
        return from.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
