package entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Subject subject;
    @Autowired
    private List<Card> cards;

}
