package com.hamidi.uniApp.repositories;

import com.hamidi.uniApp.entities.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<Server, Integer> {
}
