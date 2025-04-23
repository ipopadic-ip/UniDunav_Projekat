package com.unidunav.auth.controller;

import com.unidunav.auth.dto.LoginRequest;
import com.unidunav.auth.dto.LoginResponse;
import com.unidunav.auth.service.AuthService;
import com.unidunav.user.dto.CreateUserDTO;
import com.unidunav.user.service.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired private AuthService authService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody CreateUserDTO dto) {
        userService.createUser(dto);
        return Map.of("message", "User registered successfully");
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}