package com.treiding_broker_system.service.trade_session;

import com.treiding_broker_system.model.order.Status;
import com.treiding_broker_system.model.trade_session.SessionState;
import com.treiding_broker_system.model.trade_session.TradeSession;
import com.treiding_broker_system.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradeSessionServiceImpl implements TradeSessionService {
    private final OrderRepository orderRepository;
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
        this.cancelTheOrders();
        log.info("Trade session number {} was stopped", tradeSession.getSessionNumber());
    }

    private void cancelTheOrders() {
        var orders = orderRepository.getAll();
        if (!orders.isEmpty()) {
            orders.stream()
                    .filter(order -> order.getStatus().equals(Status.ACTIVE))
                    .forEach(order -> order.setStatus(Status.CANCELED));
        }
    }

    public boolean isSessionActive() {
        return tradeSession.isActive();
    }

    @Override
    public SessionState getSessionState() {
        return tradeSession.getSessionState();
    }
}
