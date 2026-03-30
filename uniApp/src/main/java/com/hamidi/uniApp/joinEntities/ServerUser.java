package com.hamidi.uniApp.joinEntities;

import com.hamidi.uniApp.ServerRole;
import com.hamidi.uniApp.entities.AppUser;
import com.hamidi.uniApp.entities.Server;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

    @Enumerated(EnumType.STRING)
    private ServerRole role;
}
