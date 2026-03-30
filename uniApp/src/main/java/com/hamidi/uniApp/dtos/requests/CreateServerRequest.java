package com.hamidi.uniApp.dtos.requests;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

public record CreateServerRequest(
        String name,
        String description
){
}
