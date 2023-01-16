package com.bhn.raptorauthorizationservice.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@Builder
@ToString
public class User {

    private Long id;
    private String name;
    private String email;
    private Set<Role> roles;
}
