package com.hamidi.uniApp.controllers;

import com.hamidi.uniApp.entities.Subject;

import com.hamidi.uniApp.services.ServerService;

import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectController {

    private final ServerService serverService;
    public SubjectController(ServerService serverService){this.serverService=serverService;}

    @PostMapping("/user/{user_id}/server/{server_id}/subject")
    public void createSubject(@RequestBody Subject subject, @PathVariable Integer user_id, @PathVariable Integer server_id){
        serverService.addSubject(subject , user_id, server_id);
    }
}
