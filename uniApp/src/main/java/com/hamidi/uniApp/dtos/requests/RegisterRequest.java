package com.hamidi.uniApp.dtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

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
