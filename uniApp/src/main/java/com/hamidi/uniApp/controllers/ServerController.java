package com.hamidi.uniApp.controllers;

import com.hamidi.uniApp.ServerRole;
import com.hamidi.uniApp.dtos.ServerDTO;
import com.hamidi.uniApp.dtos.ServerMemberDTO;
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

    @GetMapping("/servers/{serverName}")
    public ServerDTO getServerByName(@PathVariable String serverName){
        return serverService.getServerByName(serverName);
    }

    @GetMapping("/servers")
    public List<ServerDTO> getEnteredServers(Authentication authentication){
        return serverService.getEnteredServers(authentication.getName());
    }

    @GetMapping("/servers/{serverName}/members")
    public List<ServerMemberDTO> getServerMembers(
            Authentication authentication,
            @PathVariable String serverName
    ){
        return serverService.getServerMembers(authentication.getName(), serverName);
    }

    @PostMapping("/servers")
    public boolean createServer(
            Authentication authentication,
            @RequestBody ServerDTO request
    ){
            return serverService.createServer(authentication.getName(), request);
    }

    @PostMapping("/servers/{serverName}/members/{username}")
    public boolean addUserToServer(
            Authentication authentication,
            @PathVariable String serverName,
            @PathVariable String username
    ){
        return serverService.addUserToServer(authentication.getName(), serverName, username);
    }

    @PutMapping("/servers/{serverName}")
    public boolean updateServer(
            Authentication authentication,
            @PathVariable String serverName,
            @RequestBody ServerDTO request
    ){
        return serverService.updateServer(authentication.getName(), serverName, request);
    }

    @PutMapping("/servers/{serverName}/members/{username}")
    public boolean updateMemberRole(
            Authentication authentication,
            @PathVariable String serverName,
            @PathVariable String username,
            @RequestParam ServerRole role
    ){
        return serverService.updateMemberRole(authentication.getName(), serverName, username, role);
    }

    @DeleteMapping("/servers/{serverName}")
    public boolean deleteServer(
            Authentication authentication,
            @PathVariable String serverName
    ){
        return serverService.deleteServer(authentication.getName(), serverName);
    }

    @DeleteMapping("/servers/{serverName}/members/{username}")
    public boolean removeMember(
            Authentication authentication,
            @PathVariable String serverName,
            @PathVariable String username
    ){
        return serverService.removeMember(authentication.getName(), serverName, username);
    }
}
