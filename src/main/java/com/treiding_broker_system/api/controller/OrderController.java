package com.treiding_broker_system.api.controller;

import com.treiding_broker_system.exception.EntityNotFoundException;
import com.treiding_broker_system.model.order.Order;
import com.treiding_broker_system.model.order.Status;
import com.treiding_broker_system.model.order.TargetAction;
import com.treiding_broker_system.service.instrument.InstrumentService;
import com.treiding_broker_system.service.mapper.order.OrderResponseMapper;
import com.treiding_broker_system.service.order.OrderActionFacade;
import com.treiding_broker_system.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderActionFacade orderActionFacade;
    private final OrderResponseMapper orderResponseMapper;
    private final InstrumentService instrumentService;
    private final UserService userService;

    @GetMapping("/all")
    public ModelAndView allOrders() {
        var allOrders = orderActionFacade.getAll();
        var allOrdersResponse = orderResponseMapper.map(allOrders);

        var mav = new ModelAndView("/order/all-orders-view");
        mav.addObject("orders", allOrdersResponse);
        return mav;
    }

    @GetMapping("/userOrders/{id}")
    public ModelAndView getOrdersByUserId(@PathVariable(name = "id") Long id) {
        var allOrders = orderActionFacade.getByUserId(id);
        var allOrdersResponse = orderResponseMapper.map(allOrders);

        var mav = new ModelAndView("/order/all-orders-view");
        mav.addObject("orders", allOrdersResponse);
        return mav;
    }

    @GetMapping("/newOrderDetails/ownerId/{ownerId}")
    public ModelAndView createNewOrder(@PathVariable(name = "ownerId") Long ownerId,
                                       @RequestParam(name = "warningMessage", required = false, defaultValue = "") String warningMessage) {
        var mav = new ModelAndView("/order/new-order-details-view");
        var instruments = instrumentService.getAll();
        var userOwner = userService.getById(ownerId).orElseThrow(
                () -> new EntityNotFoundException(String.format("User with id=%s not found", ownerId)));

        mav.addObject("warningMessage", warningMessage);
        mav.addObject("userOwner", userOwner);
        mav.addObject("instruments", instruments);
        mav.addObject("actions", TargetAction.values());
        return mav;
    }

    @PostMapping("/createOrder/ownerId/{ownerId}")
    public String newOrderDetails(@PathVariable(name = "ownerId") Long ownerId,
                                  @RequestParam Map<String, String> body) {
        var owner = userService.getById(ownerId).orElseThrow(
                () -> new EntityNotFoundException(String.format("User with id=%s not found", ownerId)));

        var priceLimit = BigDecimal.valueOf(Double.parseDouble(body.get("price")));
        var action = TargetAction.valueOf(body.get("targetAction"));

        if (action.equals(TargetAction.BUY) && owner.getAvailableBalance().compareTo(priceLimit) < 0) {
            log.warn("User with id={} couldn't make the order cause he doesn't have enough money on his available balance", ownerId);
            String warningMessage = "You don't have enough free money to make the order";
            return String.format("redirect:/orders/newOrderDetails/ownerId/%s?warningMessage=%s", ownerId, warningMessage);
        }

        var instrumentId = Long.parseLong(body.get("instrument"));
        var instrument = instrumentService.getById(instrumentId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Instrument with id=%s not found", instrumentId)));

        var count = Integer.parseInt(body.get("count"));
        var expirationDate = LocalDateTime.parse(body.get("expirationDate"));

        var order = Order.builder()
                .owner(owner)
                .instrument(instrument)
                .initialCount(count)
                .currentCount(count)
                .expirationDate(expirationDate)
                .action(action)
                .status(Status.ACTIVE)
                .price(priceLimit)
                .currentPrice(priceLimit)
                .build();

        orderActionFacade.createAndExecute(order);
        return String.format("redirect:/users/profile?username=%s&password=%s", owner.getUsername(), owner.getPassword());
    }
}
