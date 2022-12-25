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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @GetMapping("/registration")
    public String registration() {
        return "/user/registration-view";
    }

    @PostMapping("/create")
    public String createUser(@RequestParam(name = "username") String username,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "balance", defaultValue = "1000.0") BigDecimal balance) {
        var userRequest = UserRequest.builder()
                .username(username)
                .password(password)
                .balance(balance)
                .build();
        var user = userRequestMapper.map(userRequest);
        userService.create(user);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "/user/login-view";
    }

    @GetMapping("/profile")
    public ModelAndView profileEnter(@RequestParam(name = "username") String username,
                                     @RequestParam(name = "password") String password) {
        var user = userService.getByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("User with username=%s not found", username)));

        if (user.getPassword().equals(password)) {
            var mav = new ModelAndView();
            var userResponse = userResponseMapper.map(user);

            if (user.getRole().equals(Role.ADMIN)) {
                mav.addObject("admin", userResponse);
                mav.setViewName("/user/admin-profile-view");
            } else {
                mav.addObject("user", userResponse);
                mav.setViewName("/user/user-profile-view");
            }
            return mav;
        }
        throw new UserAccessException("Wrong password", HttpStatus.FORBIDDEN);
    }
}
