package com.treiding_broker_system.service.user.impl;

import com.treiding_broker_system.model.user.User;
import com.treiding_broker_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserBalanceServiceImpl {
    private final UserRepository userRepository;

    public void reduceAvailableBalance(User user, BigDecimal digitToReduce) {
        user.setAvailableBalance(user.getAvailableBalance().subtract(digitToReduce));
    }
}
