package com.treiding_broker_system.service.trade_session;

import com.treiding_broker_system.model.trade_session.SessionState;

public interface TradeSessionService {
    void startSession();

    void stopSession();

    boolean isSessionActive();

    SessionState getSessionState();
}
