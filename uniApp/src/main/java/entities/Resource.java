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
        name = "resources"
)
public class Resource {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String url;

    @Autowired
    private Folder folder;
}
