package com.hamidi.uniApp.entities;


import com.hamidi.uniApp.joinEntities.ServerUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(
            nullable = false,
            unique = true
    )
    private String name;

    private String description;

    @Column(
            nullable = false
    )
    private Instant createdAt;
    @PrePersist
    private void onCreate(){
        createdAt = Instant.now();
    }

    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(mappedBy = "server", cascade = CascadeType.ALL)
    private List<ServerUser> serverUsers;

}
