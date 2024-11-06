package com.mgtu.museum.service;

import com.mgtu.museum.controller.Auth.Dto.SignInDto;
import com.mgtu.museum.entity.User;
import com.mgtu.museum.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public String signIn(SignInDto dto) throws LoginException {
        User user = userRepository.findByUsername(dto.getUsername());
        if(user == null) {
            throw new EntityNotFoundException("User not found");
        }
        if (!BCrypt.checkpw(dto.getSecret(), user.getSecret())){
            throw new LoginException("Неверный пароль");
        }
        return tokenService.generateToken(dto.getUsername());
    }
}
