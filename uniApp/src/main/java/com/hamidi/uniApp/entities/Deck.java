package com.hamidi.uniApp.entities;

import java.util.List;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(
        name = "decks"
)
public class Deck {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(
            name = "subject_id"
    )
    private Subject subject;
    @OneToMany(
            mappedBy = "deck"
    )
    private List<Card> cards;

}
