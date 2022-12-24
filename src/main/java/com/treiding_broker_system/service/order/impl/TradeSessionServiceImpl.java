package com.treiding_broker_system.service.order.impl;

import com.treiding_broker_system.model.trade_session.TradeSession;
import com.treiding_broker_system.service.order.TradeSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TradeSessionServiceImpl implements TradeSessionService {
    private TradeSession tradeSession;

    @Override
    public void startSession() {
        tradeSession.startSession();
        log.info("Trade session number {} was started", tradeSession.getSessionNumber());
    }

    @Override
    public void stopSession() {
        tradeSession.completeSession();
        log.info("Trade session number {} was stopped", tradeSession.getSessionNumber());
    }

    public boolean isSessionActive() {
        return tradeSession.isActive();
    }
}
