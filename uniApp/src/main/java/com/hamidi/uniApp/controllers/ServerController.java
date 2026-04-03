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
@RequestMapping("/private/servers")
@RequiredArgsConstructor
public class ServerController {

    private final ServerService serverService;

    @GetMapping("/{serverName}")
    public ServerDTO getServerByName(@PathVariable String serverName){
        return serverService.getServerByName(serverName);
    }

    @GetMapping("/me")
    public List<ServerDTO> getEnteredServers(Authentication authentication){
        return serverService.getEnteredServers(authentication.getName());
    }

    @GetMapping("/{serverName}/members")
    public List<ServerMemberDTO> getServerMembers(
            Authentication authentication,
            @PathVariable String serverName
    ){
        return serverService.getServerMembers(authentication.getName(), serverName);
    }

    @PostMapping("/create")
    public boolean createServer(
            Authentication authentication,
            @RequestBody ServerDTO request
    ){
            return serverService.createServer(authentication.getName(), request);
    }

    @PostMapping("/{serverName}/members/add-")
    public boolean addUserToServer(
            Authentication authentication,
            @PathVariable String serverName,
            @RequestParam String username
    ){
        return serverService.addUserToServer(authentication.getName(), serverName, username);
    }

    @PutMapping("/{serverName}")
    public boolean updateServer(
            Authentication authentication,
            @PathVariable String serverName,
            @RequestBody ServerDTO request
    ){
        return serverService.updateServer(authentication.getName(), serverName, request);
    }

    @PutMapping("/{serverName}/members/update-role-")
    public boolean updateMemberRole(
            Authentication authentication,
            @PathVariable String serverName,
            @RequestParam String username,
            @RequestParam ServerRole role
    ){
        return serverService.updateMemberRole(authentication.getName(), serverName, username, role);
    }

    @DeleteMapping("/{serverName}")
    public boolean deleteServer(
            Authentication authentication,
            @PathVariable String serverName
    ){
        return serverService.deleteServer(authentication.getName(), serverName);
    }

    @DeleteMapping("/{serverName}/members/remove-")
    public boolean removeMember(
            Authentication authentication,
            @PathVariable String serverName,
            @RequestParam String username
    ){
        return serverService.removeMember(authentication.getName(), serverName, username);
    }
}
