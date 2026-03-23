package com.hamidi.uniApp.entities;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(
        name = "servers"
)
@Data
public class Server {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "servers")
    private List<User> users = new LinkedList<>();
    @OneToMany(
            mappedBy = "server",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Subject> subjects = new LinkedList<>();
}
