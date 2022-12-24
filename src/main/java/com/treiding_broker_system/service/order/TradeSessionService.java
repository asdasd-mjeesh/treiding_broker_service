package com.treiding_broker_system.service.order;

public interface TradeSessionService {
    void startSession();

    void stopSession();

    boolean isSessionActive();
}
