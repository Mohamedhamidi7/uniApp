package com.hamidi.uniApp.entities;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(
        name = "resources"
)
public class Resource {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String url;

    @ManyToOne
    @JoinColumn(
            name = "folder_id"
    )
    private Folder folder;
}
