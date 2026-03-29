package com.hamidi.uniApp.controllers;

import com.hamidi.uniApp.dtos.requests.LoginRequest;
import com.hamidi.uniApp.dtos.requests.RegisterRequest;
import com.hamidi.uniApp.dtos.responces.AuthResponce;
import com.hamidi.uniApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class AuthController {
    public final AuthService authService;

    @PostMapping("/register")
    public AuthResponce register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponce login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
}
