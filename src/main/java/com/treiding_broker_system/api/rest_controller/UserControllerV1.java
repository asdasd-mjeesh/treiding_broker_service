package com.treiding_broker_system.api.rest_controller;

import com.treiding_broker_system.model.dto.request.UserRequest;
import com.treiding_broker_system.model.dto.response.UserResponse;
import com.treiding_broker_system.service.mapper.user.UserRequestMapper;
import com.treiding_broker_system.service.mapper.user.UserResponseMapper;
import com.treiding_broker_system.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserControllerV1 {
    private final UserService userService;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @PostMapping("/")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        var user = userRequestMapper.map(userRequest);
        userService.create(user);
        var userResponse = userResponseMapper.map(user);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable(name = "id") Long id) {
        var user = userService.getUserWithResultChecking(id);
        var userResponse = userResponseMapper.map(user);
        return ResponseEntity.ok(userResponse);
    }
}
