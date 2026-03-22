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
        name = "users"
)
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String fName;
    private String lName;

    @Autowired
    private List<Server> servers;
}
