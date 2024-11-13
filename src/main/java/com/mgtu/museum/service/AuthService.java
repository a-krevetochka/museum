package com.mgtu.museum.service;

import com.mgtu.museum.controller.Auth.request.SignInRequest;
import com.mgtu.museum.controller.Auth.response.SignInResponse;
import com.mgtu.museum.entity.User;
import com.mgtu.museum.exceptions.business.BusinessException;
import com.mgtu.museum.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public SignInResponse signIn(SignInRequest dto) throws AccessDeniedException {
        User user = userRepository.findByUsername(dto.getUsername());
        if(user == null) {
            throw new BusinessException("Такого пользователя не существует");
        }
        if (!BCrypt.checkpw(dto.getSecret(), user.getSecret())){
            throw new AccessDeniedException("Неверный пароль");
        }
        return new SignInResponse(tokenService.generateToken(dto.getUsername()));
    }
}
