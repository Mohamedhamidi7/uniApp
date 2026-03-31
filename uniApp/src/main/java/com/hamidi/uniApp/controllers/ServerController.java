package com.hamidi.uniApp.controllers;

import com.hamidi.uniApp.dtos.ServerDTO;
import com.hamidi.uniApp.services.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private")
@RequiredArgsConstructor
public class ServerController {

    private final ServerService serverService;

    @PostMapping("/servers")
    public void createServer(@RequestBody ServerDTO request, Authentication authentication){
        String username = authentication.getName();
        serverService.createServer(request, username);
    }

    @GetMapping("/servers/all")
    public List<ServerDTO> getServers(Authentication authentication){
        String username = authentication.getName();
        return serverService.getServers(username);
    }
//    @GetMapping("/servers/{servername}")
//    public List<ServerDTO> getServer(
//            @PathVariable String serverName,
//            Authentication authentication
//    ){
//        String username = authentication.getName();
//        return serverService.getServers(username , serverName);
//    }

    @PutMapping("/servers/{serverName}")
    public boolean updateServerInfo(
            @RequestBody ServerDTO request,
            @RequestParam String serverName,
            Authentication authentication
    ){
        String username = authentication.getName();
        return serverService.updateServerInfo(request, username, serverName);
    }

//    @PutMapping("/server/add")
//    public boolean giveAccessTo(
//            @RequestParam String user
//    ){
//        return false;
//    }

}
