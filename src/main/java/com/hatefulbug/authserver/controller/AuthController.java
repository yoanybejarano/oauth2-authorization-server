package com.hatefulbug.authserver.controller;

import com.hatefulbug.authserver.dto.MessageDto;
import com.hatefulbug.authserver.dto.UserDto;
import com.hatefulbug.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<MessageDto> createUser(@RequestBody UserDto userDto) {
        MessageDto result = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
