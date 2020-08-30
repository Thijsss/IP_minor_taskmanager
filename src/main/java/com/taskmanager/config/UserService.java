package com.taskmanager.config;

import com.taskmanager.dto.CreateUserDTO;
import com.taskmanager.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    UserDTO createUser(CreateUserDTO userDTO);
}
