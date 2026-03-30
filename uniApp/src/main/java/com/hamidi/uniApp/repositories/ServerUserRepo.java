package com.hamidi.uniApp.repositories;

import com.hamidi.uniApp.joinEntities.ServerUser;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServerUserRepo extends JpaRepository<ServerUser, Integer> {

    List<ServerUser> findAllByUser_id(Integer user_id);
}
