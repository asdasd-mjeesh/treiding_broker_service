package com.treiding_broker_system.service.user;

import com.treiding_broker_system.model.user.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserService {
    User create(User user);

    User getUserWithResultChecking(Long id);

    Optional<User> getById(Long id);

    void addMoneyToUserBalance(Long userId, BigDecimal money);

    void reduceMoneyFromUserBalance(Long userId, BigDecimal money);
}
