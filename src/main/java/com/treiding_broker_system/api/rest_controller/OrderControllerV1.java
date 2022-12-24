package com.treiding_broker_system.api.rest_controller;

import com.treiding_broker_system.model.dto.request.OrderRequest;
import com.treiding_broker_system.model.dto.response.OrderResponse;
import com.treiding_broker_system.service.mapper.order.OrderRequestMapper;
import com.treiding_broker_system.service.mapper.order.OrderResponseMapper;
import com.treiding_broker_system.service.order.OrderActionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderActionFacade orderActionFacade;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;

    @PostMapping("/")
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest orderRequest) {
        var order = orderRequestMapper.map(orderRequest);
        order = orderActionFacade.createAndExecute(order);
        var orderResponse = orderResponseMapper.map(order);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderResponse>> getAll() {
        var orders = orderActionFacade.getAll();
        var ordersResponse = orderResponseMapper.map(orders);
        return ResponseEntity.ok(ordersResponse);
    }
}
