package com.hamidi.uniApp.helpers;

import com.hamidi.uniApp.dtos.ServerDTO;
import com.hamidi.uniApp.entities.Server;

public class SWITCHER{
    public static ServerDTO fromSERVERtoSERVERDTO(Server input){
        return new ServerDTO(
                input.getName(),
                input.getDescription()
        );
    }
}
