package com.hamidi.uniApp.services;

import com.hamidi.uniApp.CONVERTER;
import com.hamidi.uniApp.ServerRole;
import com.hamidi.uniApp.dtos.ServerDTO;
import com.hamidi.uniApp.dtos.ServerMemberDTO;
import com.hamidi.uniApp.entities.AppUser;
import com.hamidi.uniApp.entities.Server;
import com.hamidi.uniApp.exeptions.NotFound;
import com.hamidi.uniApp.joinEntities.ServerUser;
import com.hamidi.uniApp.repositories.AppUserRepo;
import com.hamidi.uniApp.repositories.ServerRepo;
import com.hamidi.uniApp.repositories.ServerUserRepo;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerService {

    public final AppUserRepo appUserRepo;
    public final ServerRepo serverRepo;
    public final ServerUserRepo serverUserRepo;

    public AppUser getUser(String username){
        return appUserRepo.findByUsername(username)
                .orElseThrow(()->new NotFound("user_id"));
    }
    public Server getServer(String serverName){
        return serverRepo.findByName(serverName)
                .orElseThrow(()->new NotFound("user_id"));
    }
    public Integer getUserId(String username){
        return getUser(username).getId();
    }
    public Integer getServerId(String serverName){
        return getServer(serverName).getId();
    }
    public boolean isAllowed(String username, String serverName, List<ServerRole> role){
        return serverUserRepo.existsByUser_idAndServer_idAndRoleIn(
                getUserId(username),
                getServerId(serverName),
                role
        );
    }

    public ServerDTO getServerByName(String serverName) {
        return serverRepo.findByName(serverName)
                .orElseThrow(()->new NotFound("Server Not found!"))
                .toDTO();
    }

    public List<ServerDTO> getEnteredServers(String username) {
        Integer user_id = getUserId(username);
        List<Server> user_servers = serverUserRepo.findAllByUser_id(user_id)
                .stream()
                .map(ServerUser::getServer)
                .toList();
        return user_servers.stream()
                .map(Server::toDTO)
                .toList();
    }

    public List<ServerMemberDTO> getServerMembers(String user, String serverName) {
        Integer user_id = getUserId(user);
        Integer server_id = getServerId(serverName);
        boolean isMember = serverUserRepo.existsByUser_idAndServer_id(user_id,server_id);
        if (isMember)
            return serverUserRepo.findAllByServer_id(server_id).stream()
                    .map(e->{
                        return CONVERTER.toDTO(
                                e.getUser(),
                                e.getRole()
                        );
                    })
                    .toList();
        else
            return serverUserRepo.findAllByServer_idAndRoleIn(
                    server_id,
                    List.of(ServerRole.OWNER, ServerRole.CONTROLLER)
            ).stream()
                    .map(e->{
                        return CONVERTER.toDTO(
                                e.getUser(),
                                e.getRole()
                        );
                    })
                    .toList();
    }

    public boolean addUserToServer(String username, String serverName, String invitedUsername) {
        Integer user_id = getUserId(username);
        Integer invited_id = getUserId(invitedUsername);
        Integer server_id = getServerId(serverName);

        boolean isAllowed = isAllowed(
                username,
                serverName,
                List.of(ServerRole.OWNER, ServerRole.CONTROLLER)
        );

        boolean isMember = serverUserRepo.existsByUser_idAndServer_id(invited_id,server_id);

        if (isAllowed && !isMember){
            serverUserRepo.save(
                    ServerUser.builder()
                            .user(getUser(invitedUsername))
                            .server(getServer(serverName))
                            .role(ServerRole.MEMBER)
                            .build()
            );
            return true;
        }
        return false;

    }

    public boolean createServer(String username, @NonNull ServerDTO request) {
        if(serverRepo.existsByName(request.name()))
            return false;
        Server newServer = Server.builder()
                .name(request.name())
                .description(request.description())
                .build();

        AppUser user = getUser(username);
        ServerUser serverUser = ServerUser.builder()
                .user(user)
                .server(newServer)
                .role(ServerRole.OWNER)
                .build();

        serverRepo.save(newServer);
        serverUserRepo.save(serverUser);
        return true;
    }

    public boolean updateServer(String username,String serverName, ServerDTO request) {
        boolean isAllowed = isAllowed(
                username,
                serverName,
                List.of(ServerRole.OWNER)
        );

        if (!isAllowed)
            return false;

        Server update = getServer(serverName);
        update.setName(request.name());
        if (request.description().isEmpty() || request.description().isBlank())
            update.setName(request.description());

        return true;
    }

    public boolean updateMemberRole(String username, String serverName, String memberUsername, ServerRole role) {
        boolean isAllowed = isAllowed(username, serverName, List.of(ServerRole.OWNER));
        boolean isMember = serverUserRepo.existsByUser_idAndServer_id(getUserId(memberUsername),getServerId(serverName));
        if (!isMember || !isAllowed)
            return false;

        ServerUser update = serverUserRepo.findByUser_idAndServer_id(getUserId(memberUsername), getServerId(serverName))
                .orElseThrow(()->new NotFound(memberUsername + " is Not a Member"));
        update.setRole(role);
        serverUserRepo.save(update);
        return true;
    }

    public boolean deleteServer(String username, String serverName) {
        boolean isAllowed = isAllowed(username, serverName, List.of(ServerRole.OWNER));
        if (!isAllowed)
            return false;
        serverRepo.delete(getServer(serverName));
        return true;
    }

    public boolean removeMember(String username, String serverName, String memberUsername) {
        boolean isAllowed = isAllowed(username, serverName, List.of(ServerRole.OWNER, ServerRole.CONTROLLER));
        if (!isAllowed)
            return false;
        ServerUser serverUser = serverUserRepo.findByUser_idAndServer_id(getUserId(username), getServerId(serverName))
                        .orElseThrow(()->new NotFound(memberUsername + " is Not a Member"));
        serverUserRepo.delete(serverUser);
        return true;
    }
}
