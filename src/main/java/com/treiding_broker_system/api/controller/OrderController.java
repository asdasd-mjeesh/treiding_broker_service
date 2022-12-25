package com.treiding_broker_system.api.controller;

import com.treiding_broker_system.service.mapper.order.OrderRequestMapper;
import com.treiding_broker_system.service.mapper.order.OrderResponseMapper;
import com.treiding_broker_system.service.order.OrderActionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderActionFacade orderActionFacade;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;

    @GetMapping("/all")
    public ModelAndView allOrders() {
        var allOrders = orderActionFacade.getAll();
        var allOrdersResponse = orderResponseMapper.map(allOrders);

        var mav = new ModelAndView("/order/all-orders-view");
        mav.addObject("orders", allOrdersResponse);
        return mav;
    }

    @PostMapping("/newOrderDetails")
    public String newOrderDetails() {
        return "/order/new-order-details-view";
    }

    @PostMapping("/createNew")
    public String createNewOrder(@RequestParam Map<String, String> body) {
        BigDecimal balance = BigDecimal.valueOf(Long.parseLong(body.get("balance")));
        System.out.println(balance);
        return null;
    }
}
