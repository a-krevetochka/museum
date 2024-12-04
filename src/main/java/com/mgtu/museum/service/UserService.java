package com.mgtu.museum.service;

import com.mgtu.museum.controller.UserController.request.CreateUserRequest;
import com.mgtu.museum.controller.UserController.request.ResetPasswordRequest;
import com.mgtu.museum.controller.UserController.request.UpdateUserRequest;
import com.mgtu.museum.controller.UserController.response.GetUserResponse;
import com.mgtu.museum.entity.Role;
import com.mgtu.museum.entity.User;
import com.mgtu.museum.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public void createUser(CreateUserRequest dto){
        if (userRepository.findByUsername(dto.getUsername()) != null) {
            throw new IllegalArgumentException("Пользователь уже существует");
        }
        dto.setSecret(BCrypt.hashpw(dto.getSecret(), BCrypt.gensalt()));
        userRepository.save(mapper.map(dto, User.class));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public void changePassword(ResetPasswordRequest dto) throws AccessDeniedException {
        User user = userRepository.findByUsername(dto.getUsername());
        if(user == null) {
            throw new AccessDeniedException("Неверный логин или пароль");
        }
        if (!BCrypt.checkpw(dto.getPreviousPassword(), user.getSecret())){
            throw new AccessDeniedException("Неверный логин или пароль");
        }
        user.setSecret(BCrypt.hashpw(dto.getNewPassword(), BCrypt.gensalt()));
        userRepository.changePassword(user);
    }

    public void updateUser(UpdateUserRequest dto) {
        userRepository.updateUser(User.builder()
                .id(dto.getUserId())
                .name(dto.getName())
                .role(new Role(dto.getRole()))
                .lastName(dto.getLastName())
                .middleName(dto.getMiddleName())
                .build()
        );
    }

    public List<GetUserResponse> getAll() {
        return userRepository.getAll().stream().map(u -> mapper.map(u, GetUserResponse.class)).toList();
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }

    public GetUserResponse getById(Integer id) {
        return mapper.map(userRepository.getById(id), GetUserResponse.class);
    }
}
