package com.hamidi.uniApp.services;

import com.hamidi.uniApp.entities.User;
import com.hamidi.uniApp.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUser(Integer user_id){
        return userRepository.getReferenceById(user_id);
    }
}
