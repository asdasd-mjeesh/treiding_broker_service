package com.treiding_broker_system.service.mapper.user;

import com.treiding_broker_system.model.dto.response.UserResponse;
import com.treiding_broker_system.model.user.User;
import com.treiding_broker_system.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserResponseMapper implements Mapper<UserResponse, User> {

    @Override
    public UserResponse map(User from) {
        return UserResponse.builder()
                .id(from.getId())
                .username(from.getUsername())
                .balance(from.getBalance())
                .role(from.getRole())
                .build();
    }

    @Override
    public List<UserResponse> map(List<User> from) {
        return from.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
