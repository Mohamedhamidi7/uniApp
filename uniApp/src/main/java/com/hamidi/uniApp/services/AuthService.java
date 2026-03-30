package com.hamidi.uniApp.services;

import com.hamidi.uniApp.dtos.requests.LoginRequest;
import com.hamidi.uniApp.dtos.requests.RegisterRequest;
import com.hamidi.uniApp.dtos.responces.AuthResponce;
import com.hamidi.uniApp.entities.AppUser;
import com.hamidi.uniApp.repositories.AppUserRepo;
import com.hamidi.uniApp.security.CustomUserDetails;
import com.hamidi.uniApp.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    public final AppUserRepo appUserRepo;
    public final PasswordEncoder passwordEncoder;
    public final JwtService jwtService;
    public final AuthenticationManager authenticationManager;

    public AuthResponce register(RegisterRequest request){
        if (appUserRepo.existsByUsername(request.username())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }

        AppUser user = AppUser.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .birthday(request.birthday())
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();


        appUserRepo.save(user);

        String jwt = jwtService.generateToken(new CustomUserDetails(user));

        return new AuthResponce(jwt);
    }

    public AuthResponce login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        AppUser user = appUserRepo.findByUsername(request.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE));

        String jwt = jwtService.generateToken(new CustomUserDetails(user));

        return new AuthResponce(jwt);
    }

}
