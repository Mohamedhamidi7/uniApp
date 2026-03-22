package com.hamidi.uniApp.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(
        name = "users"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(
            nullable = false
    )
    private String fName;
    @Column(
            nullable = false
    )
    private String lName;
    @Column(
            nullable = false,
            unique = true
    )
    private String username;
    @Column(
            nullable = false,
            unique = true
    )
    private String email;
    @Column(
            nullable = false
    )
    private String password;


    @ManyToMany
    @JoinTable(
            name = "users_servers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "server_id")
    )
    private List<Server> servers;
}
