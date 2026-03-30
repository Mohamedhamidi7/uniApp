package com.hamidi.uniApp.repositories;

import com.hamidi.uniApp.entities.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Integer> {
}
