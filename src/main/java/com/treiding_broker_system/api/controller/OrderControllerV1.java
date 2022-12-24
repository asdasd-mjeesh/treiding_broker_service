package com.treiding_broker_system.api.controller;

import com.treiding_broker_system.service.OrderExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderExecutionService orderExecutionService;


}
