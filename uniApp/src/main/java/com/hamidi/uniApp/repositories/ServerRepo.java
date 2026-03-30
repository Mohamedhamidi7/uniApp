package com.hamidi.uniApp.repositories;

import com.hamidi.uniApp.entities.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServerRepo extends JpaRepository<Server, Integer> {

    public List<Server> findAllById(Integer username);

}
