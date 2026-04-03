package com.hamidi.uniApp.dtos.requests;


import java.time.LocalDate;

public record RegisterRequest(
        String firstname,
        String lastname,
        LocalDate birthday,
        String username,
        String email,
        String password
) {
}
