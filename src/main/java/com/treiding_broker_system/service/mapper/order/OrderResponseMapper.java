package com.treiding_broker_system.service.mapper.order;

import com.treiding_broker_system.model.dto.response.OrderResponse;
import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.service.mapper.Mapper;
import com.treiding_broker_system.service.mapper.user.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderResponseMapper implements Mapper<OrderResponse, Order> {
    private final UserResponseMapper userResponseMapper;

    @Override
    public OrderResponse map(Order from) {
        return OrderResponse.builder()
                .id(from.getId())
                .instrument(from.getInstrument())
                .count(from.getInitialCount())
                .price(from.getPrice())
                .action(from.getAction())
                .status(from.getStatus())
                .expirationDate(from.getExpirationDate())
                .owner(userResponseMapper.map(from.getOwner()))
                .build();
    }

    @Override
    public List<OrderResponse> map(List<Order> from) {
        return from.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
