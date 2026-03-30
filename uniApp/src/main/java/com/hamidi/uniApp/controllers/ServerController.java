package com.hamidi.uniApp.controllers;

import com.hamidi.uniApp.dtos.requests.CreateServerRequest;
import com.hamidi.uniApp.dtos.responces.ServerDTO;
import com.hamidi.uniApp.services.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private")
@RequiredArgsConstructor
public class ServerController {

    private final ServerService serverService;


    @PostMapping("/servers")
    public void createServer(@RequestBody CreateServerRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        serverService.createServer(request, username);
    }

    @GetMapping("/servers")
    public List<ServerDTO> getServers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return serverService.getServersOf(username);
    }

}
