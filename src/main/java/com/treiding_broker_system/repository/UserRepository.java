package com.treiding_broker_system.repository;

import com.treiding_broker_system.exception.EntityNotFoundException;
import com.treiding_broker_system.model.user.Role;
import com.treiding_broker_system.model.user.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private static Long USER_COUNT = 0L;
    private final List<User> users = new ArrayList<>();

    @PostConstruct
    private void init() {
        users.add(User.builder()
                .id(++USER_COUNT)
                .username("admin")
                .password("admin")
                .role(Role.ADMIN)
                .balance(BigDecimal.ZERO)
                .build());
    }

    public User create(User user) {
        user.setId(++USER_COUNT);
        users.add(user);
        return user;
    }

    public User getUserWithResultChecking(Long id) {
        var maybeUser = this.getById(id);
        return maybeUser.orElseThrow(() -> new EntityNotFoundException(String.format("User with id=%s not found", id)));
    }

    public Optional<User> getById(Long id) {
        return this.users.stream()
                .filter(user -> user.getId().equals(id))
                .findAny();
    }

    public Optional<User> getByUsername(String username) {
        return this.users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny();
    }

    public void update(User user) {
        // It's unnecessary action because I change value by the link,
        // but anyway I've created this method for logic demonstration
        // and potential future migration data storing to the database
    }
}
