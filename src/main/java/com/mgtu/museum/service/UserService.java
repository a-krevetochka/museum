package com.mgtu.museum.service;

import com.mgtu.museum.controller.Auth.Dto.SignInDto;
import com.mgtu.museum.controller.UserController.dto.CreateUserDto;
import com.mgtu.museum.entity.User;
import com.mgtu.museum.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public void createUser(CreateUserDto dto) {
        if (userRepository.findByUsername(dto.getUsername()) != null) {
            throw new EntityExistsException("Username already exists");
        }
        dto.setSecret(BCrypt.hashpw(dto.getSecret(), BCrypt.gensalt()));
        userRepository.save(mapper.map(dto, User.class));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
