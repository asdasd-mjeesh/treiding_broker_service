package com.treiding_broker_system.model.trade_session;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
public class TradeSession {
    private static Integer SESSION_NUMBER = 0;
    private boolean isActive = false;
    private SessionState sessionState = SessionState.STOPPED;
    private LocalDateTime sessionStartDate;
    private LocalDateTime sessionEndDate;

    public void startSession() {
        sessionStartDate = LocalDateTime.now();
        sessionState = SessionState.ACTIVE;
        isActive = true;
        SESSION_NUMBER++;
    }

    public void completeSession() {
        sessionEndDate = LocalDateTime.now();
        this.sessionState = SessionState.STOPPED;
        this.isActive = false;
    }

    public Integer getSessionNumber() {
        return SESSION_NUMBER;
    }
}
