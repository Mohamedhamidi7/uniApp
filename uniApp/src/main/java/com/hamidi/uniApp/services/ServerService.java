package com.hamidi.uniApp.services;

import com.hamidi.uniApp.ServerRole;
import com.hamidi.uniApp.dtos.ServerDTO;
import com.hamidi.uniApp.entities.AppUser;
import com.hamidi.uniApp.entities.Server;
import com.hamidi.uniApp.helpers.SWITCHER;
import com.hamidi.uniApp.joinEntities.ServerUser;
import com.hamidi.uniApp.repositories.AppUserRepo;
import com.hamidi.uniApp.repositories.ServerRepo;
import com.hamidi.uniApp.repositories.ServerUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerService {

    private final ServerUserService serverUserService; //3
    private final AppUserService appUserService; //2
    private final ServerRepo serverRepo;
    private final AppUserRepo appUserRepo;
    private final ServerUserRepo serverUserRepo;

    public Integer getIdOf(String name){
        return getServer(name).getId();
    }

    public Server getServer(Integer id){
        return serverRepo.findById(id)
                .orElseThrow(()->new UsernameNotFoundException("Server Not Found"));
    }
    public Server getServer(String name){
        return serverRepo.findByName(name)
                .orElseThrow(()->new UsernameNotFoundException("Server Not Found"));
    }

    public void createServer(ServerDTO request, String username){

        AppUser user = appUserService.getUser(username);

        Server server = Server.builder()
                .name(request.name())
                .description(request.description())
                .build();

        ServerUser serverUser = ServerUser.builder()
                .user(user)
                .server(server)
                .role(ServerRole.OWNER)
                .build();

        serverRepo.save(server);
        serverUserRepo.save(serverUser);

    }

    public List<ServerDTO> getServers(String username){
        return serverUserService.getServersOf(
                    appUserService.getIdOf(username)
                )
                .stream()
                .map(SWITCHER::fromSERVERtoSERVERDTO)
                .toList();
    }
//    public ServerDTO getServer(String name){
//
//    }

    public boolean updateServerInfo(ServerDTO request, String username, String targetName) {
       Integer user_id = appUserService.getIdOf(username);
       Server update = serverUserService.getServersOf(user_id).stream()
               .filter(e -> e.getName().equals(targetName))
               .toList()
               .get(0);
       Integer server_id = update.getId();
       ServerRole role = serverUserService.getRoleBetween(user_id, server_id);
       boolean isMatched = ( role == ServerRole.OWNER ) ||  ( role == ServerRole.EDITOR );
       if ( isMatched ){
           update.setName(request.name());
           if (!request.description().isBlank())
               update.setDescription(request.name());;
           serverRepo.save(update);
           return true;
       }
       return false;
    }

}
