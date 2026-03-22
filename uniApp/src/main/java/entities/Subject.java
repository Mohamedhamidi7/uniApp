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
        name = "subjects"
)
public class Subject {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @Autowired
    private Server server;
    @Autowired
    private List<Deck> decks;
    @Autowired
    private List<Post> posts;
    @Autowired
    private List<Folder> folders;
}
