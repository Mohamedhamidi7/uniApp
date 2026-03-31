package com.hamidi.uniApp.repositories;

import com.hamidi.uniApp.joinEntities.ServerUser;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServerUserRepo extends JpaRepository<ServerUser, Integer> {

    List<ServerUser> findAllByUser_id(Integer user_id);
    Optional<ServerUser> findByUser_idAndServer_id(Integer user_id, Integer server_id);
}
