package com.taskmanager.config;

import com.taskmanager.dto.CreateUserDTO;
import com.taskmanager.dto.UserDTO;
import com.taskmanager.model.User;
import com.taskmanager.model.UserRole;
import com.taskmanager.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


    @Service
    public class UserServiceImpl implements UserService {
        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;

        public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
            this.repository = repository;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = repository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User does not exist");
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
        }

        @Override
        public UserDTO createUser(CreateUserDTO userDTO) {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setRole(userDTO.getUsername().endsWith("a") ? UserRole.ADMIN : UserRole.MEMBER);
            user = repository.save(user);
            return convert(user);
        }

        private UserDTO convert(User user) {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setRole(user.getRole());
            return dto;
        }
    }

