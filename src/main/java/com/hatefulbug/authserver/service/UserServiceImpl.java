package com.hatefulbug.authserver.service;

import com.hatefulbug.authserver.dto.MessageDto;
import com.hatefulbug.authserver.dto.UserDto;
import com.hatefulbug.authserver.entity.Role;
import com.hatefulbug.authserver.entity.User;
import com.hatefulbug.authserver.enums.RoleName;
import com.hatefulbug.authserver.repository.RoleRepository;
import com.hatefulbug.authserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public MessageDto createUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.username())
                .password(passwordEncoder.encode(userDto.password()))
                .build();
        Set<Role> roles = new HashSet<>();
        userDto.roles().forEach(r -> {
            Role role = roleRepository.findByRole(RoleName.valueOf(r)).orElseThrow(()-> new RuntimeException("Role not found"));
            roles.add(role);
        });
        user.setRoles(roles);
        userRepository.save(user);
        return new MessageDto(String.format("User %s created", userDto.username()));
    }

}
