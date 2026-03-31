package com.hamidi.uniApp.services;

import com.hamidi.uniApp.entities.AppUser;
import com.hamidi.uniApp.repositories.AppUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {
    public final AppUserRepo appUserRepo;

    public Integer getIdOf(String username){
            return appUserRepo.findByUsername(username)
                    .orElseThrow(()-> new UsernameNotFoundException("Username: " + username + " Is Not Found"))
                    .getId();
    }
    public AppUser getUser(String username){
        return appUserRepo.findById(getIdOf(username))
                .orElseThrow(()->new UsernameNotFoundException("user not found!"));
    }
    public AppUser getUser(Integer id){
        return appUserRepo.findById(id)
                .orElseThrow(()->new UsernameNotFoundException("user not found!"));
    }

}
