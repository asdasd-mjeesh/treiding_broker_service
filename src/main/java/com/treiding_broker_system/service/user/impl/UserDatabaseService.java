package com.treiding_broker_system.service.user.impl;

import com.treiding_broker_system.model.user.User;
import com.treiding_broker_system.repository.UserRepository;
import com.treiding_broker_system.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDatabaseService implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }

    @Override
    public User getUserWithResultChecking(Long id) {
        return userRepository.getUserWithResultChecking(id);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    // @Transactional
    public void addMoneyToUserBalance(Long userId, BigDecimal money) {
        var user = userRepository.getUserWithResultChecking(userId);
        user.setBalance(user.getBalance().add(money));
        userRepository.update(user);
    }

    @Override
    // @Transactional
    public void reduceMoneyFromUserBalance(Long userId, BigDecimal money) {
        var user = userRepository.getUserWithResultChecking(userId);
        user.setBalance(user.getBalance().subtract(money));
        userRepository.update(user);
    }
}
