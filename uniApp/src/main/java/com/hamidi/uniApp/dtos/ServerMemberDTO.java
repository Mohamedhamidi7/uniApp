package com.hamidi.uniApp.dtos;

import com.hamidi.uniApp.ServerRole;

public record ServerMemberDTO (
        String username,
        String firstname,
        String lastname,
        ServerRole role
){
}
