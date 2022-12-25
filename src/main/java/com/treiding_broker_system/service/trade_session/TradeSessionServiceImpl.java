package com.treiding_broker_system.service.trade_session;

import com.treiding_broker_system.model.trade_session.SessionState;
import com.treiding_broker_system.model.trade_session.TradeSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class TradeSessionServiceImpl implements TradeSessionService {
    private TradeSession tradeSession;

    @PostConstruct
    private void init() {
        this.tradeSession = new TradeSession();
    }

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

    @Override
    public SessionState getSessionState() {
        return tradeSession.getSessionState();
    }
}
