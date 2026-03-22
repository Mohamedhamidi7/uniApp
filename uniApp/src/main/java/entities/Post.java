package entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Subject subject;
}
