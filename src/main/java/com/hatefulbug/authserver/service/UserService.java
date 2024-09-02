package com.hatefulbug.authserver.service;


import com.hatefulbug.authserver.dto.MessageDto;
import com.hatefulbug.authserver.dto.UserDto;

public interface UserService {

    MessageDto createUser(UserDto userDto);

}
