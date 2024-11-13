package com.mgtu.museum.service;

import com.mgtu.museum.controller.Auth.request.SignInRequest;
import com.mgtu.museum.controller.Auth.response.SignInResponse;
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

    public SignInResponse signIn(SignInRequest dto) throws LoginException {
        User user = userRepository.findByUsername(dto.getUsername());
        if(user == null) {
            throw new EntityNotFoundException("User not found");
        }
        if (!BCrypt.checkpw(dto.getSecret(), user.getSecret())){
            throw new LoginException("Неверный пароль");
        }
        return new SignInResponse(tokenService.generateToken(dto.getUsername()));
    }
}
