package com.hamidi.uniApp.entities;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(
        name = "posts"
)
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(
            name = "subject_id"
    )
    private Subject subject;
}
