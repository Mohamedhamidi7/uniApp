package com.hamidi.uniApp.services;

import com.hamidi.uniApp.ServerRole;
import com.hamidi.uniApp.entities.AppUser;
import com.hamidi.uniApp.entities.Server;
import com.hamidi.uniApp.joinEntities.ServerUser;
import com.hamidi.uniApp.repositories.ServerUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerUserService {

    public final ServerUserRepo serverUserRepo;

    public List<Server> getServersOf(Integer id){
        return serverUserRepo.findAllByUser_id(id)
                .stream()
                .map(ServerUser::getServer)
                .toList();
    }
    public ServerRole getRoleBetween(Integer user_id, Integer server_id){
        return serverUserRepo.findByUser_idAndServer_id(user_id, server_id)
                .orElseThrow()
                .getRole();
    }

}
