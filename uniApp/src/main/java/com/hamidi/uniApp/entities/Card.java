package com.hamidi.uniApp.entities;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(
        name = "cards"
)
public class Card {

    @Id
    @GeneratedValue
    private Integer id;
    private String question;
    private String answer;

    @ManyToOne
    @JoinColumn(
            name = "deck_id"
    )
    private Deck deck;
}
