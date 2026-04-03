package com.hamidi.uniApp.security;

import com.hamidi.uniApp.entities.AppUser;
import com.hamidi.uniApp.repositories.AppUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserRepo appUserRepo;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found!"));

        return new CustomUserDetails(user);
    }
}
