package com.hamidi.uniApp.repositories;

import com.hamidi.uniApp.ServerRole;
import com.hamidi.uniApp.joinEntities.ServerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServerUserRepo extends JpaRepository<ServerUser, Integer> {

    List<ServerUser> findAllByUser_id(Integer user_id);
    List<ServerUser> findAllByServer_id(Integer server_id);
    List<ServerUser> findAllByServer_idAndRoleIn(Integer server_id, List<ServerRole> roles);
    boolean existsByUser_idAndServer_id(Integer user_id, Integer server_id);
    boolean existsByUser_idAndServer_idAndRoleIn(Integer user_id , Integer server_id, List<ServerRole> roles);

    Optional<ServerUser> findByUser_idAndServer_id(Integer userId, Integer serverId);
}
