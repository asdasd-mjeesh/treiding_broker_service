package com.treiding_broker_system.service.mapper.user;

import com.treiding_broker_system.model.dto.request.UserRequest;
import com.treiding_broker_system.model.user.Role;
import com.treiding_broker_system.model.user.User;
import com.treiding_broker_system.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRequestMapper implements Mapper<User, UserRequest> {

    @Override
    public User map(UserRequest from) {
        return User.builder()
                .id(from.getId())
                .username(from.getUsername())
                .password(from.getPassword())
                .balance(from.getBalance())
                .availableBalance(from.getBalance())
                .role(Role.USER)
                .build();
    }

    @Override
    public List<User> map(List<UserRequest> from) {
        return from.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
