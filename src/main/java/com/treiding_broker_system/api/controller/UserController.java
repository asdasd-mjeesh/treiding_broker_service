package com.treiding_broker_system.api.controller;

import com.treiding_broker_system.exception.EntityNotFoundException;
import com.treiding_broker_system.exception.UserAccessException;
import com.treiding_broker_system.model.dto.request.UserRequest;
import com.treiding_broker_system.model.user.Role;
import com.treiding_broker_system.service.mapper.user.UserRequestMapper;
import com.treiding_broker_system.service.mapper.user.UserResponseMapper;
import com.treiding_broker_system.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @GetMapping("/registration")
    public String registration() {
        return "registration-view";
    }

    @PostMapping("/create-user")
    public String createUser(@RequestParam Map<String, String> body) {
        var userRequest = UserRequest.builder()
                .username(body.get("username"))
                .balance(BigDecimal.valueOf(Double.parseDouble(body.get("balance"))))
                .password(body.get("password"))
                .build();
        var user = userRequestMapper.map(userRequest);
        userService.create(user);
        return "login-view";
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> body, Model model) {
        var username = body.get("username");
        var user = userService.getByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("User with username=%s not found", username)));

        var password = body.get("password");
        if (user.getPassword().equals(password)) {
            var userResponse = userResponseMapper.map(user);
            if (user.getRole().equals(Role.USER)) {
                model.addAttribute("user", userResponse);
                return "user-profile-view";
            }
            model.addAttribute("admin", userResponse);
            return "admin-profile-view";
        }
        throw new UserAccessException("Wrong password", HttpStatus.FORBIDDEN);
    }
}
