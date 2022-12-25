package com.treiding_broker_system.api.controller;

import com.treiding_broker_system.model.order.TargetAction;
import com.treiding_broker_system.service.instrument.InstrumentService;
import com.treiding_broker_system.service.mapper.order.OrderRequestMapper;
import com.treiding_broker_system.service.mapper.order.OrderResponseMapper;
import com.treiding_broker_system.service.order.OrderActionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderActionFacade orderActionFacade;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;
    private final InstrumentService instrumentService;

    @GetMapping("/all")
    public ModelAndView allOrders() {
        var allOrders = orderActionFacade.getAll();
        var allOrdersResponse = orderResponseMapper.map(allOrders);

        var mav = new ModelAndView("/order/all-orders-view");
        mav.addObject("orders", allOrdersResponse);
        return mav;
    }

    @GetMapping("/newOrderDetails/ownerId/{ownerId}")
    public ModelAndView createNewOrder(@PathVariable(name = "ownerId") Long ownerId) {
        var mav = new ModelAndView("/order/new-order-details-view");
        var instruments = instrumentService.getAll();

        mav.addObject("owner_id", ownerId);
        mav.addObject("instruments", instruments);
        mav.addObject("actions", TargetAction.values());
        return mav;
    }

    @PostMapping("/createOrder")
    public String newOrderDetails(@RequestParam Map<String, String> body) {
        System.out.println(body);
        return "okaaay!";
    }
}
