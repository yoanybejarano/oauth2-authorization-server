package com.hatefulbug.authserver.dto;

import java.util.List;

public record UserDto (
        String username,
        String password,
        List<String> roles
){}
