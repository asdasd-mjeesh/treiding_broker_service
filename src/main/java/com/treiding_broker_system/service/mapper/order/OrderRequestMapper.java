package com.treiding_broker_system.service.mapper.order;

import com.treiding_broker_system.model.dto.request.OrderRequest;
import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.Status;
import com.treiding_broker_system.service.mapper.Mapper;
import com.treiding_broker_system.service.mapper.user.UserRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderRequestMapper implements Mapper<Order, OrderRequest> {
    private final UserRequestMapper userRequestMapper;

    @Override
    public Order map(OrderRequest from) {
        return Order.builder()
                .instrument(from.getTargetInstrument())
                .initialCount(from.getCount())
                .currentCount(from.getCount())
                .action(from.getAction())
                .expirationDate(from.getExpirationDate())
                .price(from.getPrice())
                .status(Status.ACTIVE)
                .owner(userRequestMapper.map(from.getOwner()))
                .build();
    }

    @Override
    public List<Order> map(List<OrderRequest> from) {
        return from.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
