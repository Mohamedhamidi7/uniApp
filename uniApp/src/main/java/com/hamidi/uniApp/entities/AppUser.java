package com.hamidi.uniApp.entities;

import com.hamidi.uniApp.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(
            nullable = false
    )
    private String firstname;

    @NotBlank
    @Column(
            nullable = false
    )
    private String lastname;

    @NotNull
    @Column(
            nullable = false
    )
    private LocalDate birthday;

    @NotBlank
    @Column(
            nullable = false,
            unique = true
    )
    private String username;

    @NotBlank
    @Email
    @Column(
            nullable = false,
            unique = true
    )
    private String email;

    @NotBlank
    @Column(
            nullable = false
    )
    private String password;

    @Builder.Default
    private boolean enabled = false;

    @Builder.Default
    private Role role = Role.USER;

    @Column(
            nullable = false
    )
    private Instant createdAt;
    @PrePersist
    private void onCreate(){
        createdAt = Instant.now();
    }
}
