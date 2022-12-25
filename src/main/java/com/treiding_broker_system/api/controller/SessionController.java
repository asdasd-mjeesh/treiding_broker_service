package com.treiding_broker_system.api.controller;

import com.treiding_broker_system.service.trade_session.TradeSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final TradeSessionService tradeSessionService;

    @GetMapping("/start")
    public String startSession() {
        tradeSessionService.startSession();
        return "redirect:/users/profile?username=admin&password=admin";
    }

    @GetMapping("/stop")
    public String stopSession() {
        tradeSessionService.stopSession();
        return "redirect:/users/profile?username=admin&password=admin";
    }
}
