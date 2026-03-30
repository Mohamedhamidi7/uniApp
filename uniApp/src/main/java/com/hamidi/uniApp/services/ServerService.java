package com.hamidi.uniApp.services;

import com.hamidi.uniApp.ServerRole;
import com.hamidi.uniApp.dtos.requests.CreateServerRequest;
import com.hamidi.uniApp.dtos.responces.ServerDTO;
import com.hamidi.uniApp.entities.AppUser;
import com.hamidi.uniApp.entities.Server;
import com.hamidi.uniApp.joinEntities.ServerUser;
import com.hamidi.uniApp.repositories.AppUserRepo;
import com.hamidi.uniApp.repositories.ServerRepo;
import com.hamidi.uniApp.repositories.ServerUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServerService {

    private final ServerRepo serverRepo;
    private final AppUserRepo appUserRepo;
    private final ServerUserRepo serverUserRepo;

    public void createServer(CreateServerRequest request, String username){

        AppUser user = appUserRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("user not found!"));

        Server server = Server.builder()
                .name(request.name())
                .description(request.description())
                .build();

        serverRepo.save(server);

        ServerUser serverUser = ServerUser.builder()
                .user(user)
                .server(server)
                .role(ServerRole.OWNER)
                .build();

        serverUserRepo.save(serverUser);

    }

    public List<ServerDTO> getServersOf(String username){
        Integer user_id = appUserRepo.findByUsername(username)
                .orElseThrow( ()-> new UsernameNotFoundException("User Not Found!"))
                .getId();

        return serverUserRepo.findAllByUser_id(user_id).stream()
                .map(e-> e.getServer())
                .map(e -> new ServerDTO(
                        e.getName(),
                        e.getDescription()
                ) )
                .toList();

    }
}
