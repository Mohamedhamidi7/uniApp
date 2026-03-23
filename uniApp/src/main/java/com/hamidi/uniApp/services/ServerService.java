package com.hamidi.uniApp.services;

import com.hamidi.uniApp.entities.Server;
import com.hamidi.uniApp.entities.Subject;
import com.hamidi.uniApp.entities.User;
import com.hamidi.uniApp.repositories.ServerRepository;
import com.hamidi.uniApp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class ServerService {

    private final ServerRepository serverRepository;
    private final UserService userService;

    public ServerService(ServerRepository serverRepository, UserService userService){
        this.serverRepository=serverRepository;
        this.userService=userService;
    }


    public void create(Server server, Integer user_id){
        User user = userService.getUser(user_id);
        user.getServers().add(server);
        server.getUsers().add(user);
        serverRepository.save(server);
    }

    public void addSubject(Subject subject, Integer user_id, Integer server_id){
        User user = userService.getUser(user_id);
        List<Server> servers = user.getServers();
        for(Server server : servers){
            if(Objects.equals(server.getId(), server_id)){

                server.getSubjects().add(subject);
                subject.setServer(server);
                serverRepository.save(server);
            }
        }


    }
}
