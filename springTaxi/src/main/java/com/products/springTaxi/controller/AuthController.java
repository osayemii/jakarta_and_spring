package com.products.springTaxi.controller;

import org.springframework.web.bind.annotation.*;

import com.products.springTaxi.dto.LoginRequest;
import com.products.springTaxi.dto.LoginResponse;
import com.products.springTaxi.dto.RegisterRequest;
import com.products.springTaxi.entity.User;
import com.products.springTaxi.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) { // Constructor Injection
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

}
