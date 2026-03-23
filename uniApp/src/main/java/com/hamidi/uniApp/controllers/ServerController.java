package com.hamidi.uniApp.controllers;

import com.hamidi.uniApp.entities.Server;
import com.hamidi.uniApp.services.ServerService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServerController {

    private final ServerService serverService;

    public ServerController(ServerService serverService){
        this.serverService = serverService;
    }

    @PostMapping("/user/{user_id}/server")
    public void createServer(@RequestBody Server server, @PathVariable Integer user_id){
        serverService.create(server,user_id);
    }


}
