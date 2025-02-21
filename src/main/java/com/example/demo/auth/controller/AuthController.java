package com.example.demo.auth.controller;

import com.example.demo.auth.dto.AuthSignupRequestDto;
import com.example.demo.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void singup(@RequestBody AuthSignupRequestDto dto) {
        authService.signup();
    }
}
