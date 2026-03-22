package com.hamidi.uniApp.entities;

import java.util.List;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(
        name = "servers"
)
public class Server {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "servers")
    private List<User> users;
    @OneToMany(
            mappedBy = "server",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Subject> subjects;
}
