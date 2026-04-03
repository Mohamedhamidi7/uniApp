package com.hamidi.uniApp;

import com.hamidi.uniApp.dtos.ServerMemberDTO;
import com.hamidi.uniApp.entities.AppUser;

public class CONVERTER {
    public static ServerMemberDTO toDTO(AppUser user, ServerRole role){
        return new ServerMemberDTO(
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                role
        );
    }
}
