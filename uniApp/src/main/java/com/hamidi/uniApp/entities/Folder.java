package com.hamidi.uniApp.entities;

import java.util.List;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(
        name = "folders"
)
public class Folder {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "subject_id"
    )
    private Subject subject;
    @OneToMany(
            mappedBy = "folder",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Resource> resources;
}
