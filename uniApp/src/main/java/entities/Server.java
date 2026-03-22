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
        name = "servers"
)
public class Server {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @Autowired
    private List<User> users;
    @Autowired
    private List<Subject> subjects;
}
