package com.hamidi.uniApp.repositories;

import com.hamidi.uniApp.joinEntities.ServerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerUserRepo extends JpaRepository<ServerUser, Integer> {
}
