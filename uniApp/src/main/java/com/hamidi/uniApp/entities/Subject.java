package com.hamidi.uniApp.entities;

import java.util.List;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(
        name = "subjects"
)
public class Subject {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;
    @OneToMany(
            mappedBy = "subject"
    )
    private List<Deck> decks;
    @OneToMany(
            mappedBy = "subject"
    )
    private List<Post> posts;
    @OneToMany(
            mappedBy = "subject"
    )
    private List<Folder> folders;
}
