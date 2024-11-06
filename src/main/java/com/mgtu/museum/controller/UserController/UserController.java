package com.mgtu.museum.controller.UserController;

import com.mgtu.museum.controller.Response;
import com.mgtu.museum.controller.UserController.dto.CreateUserDto;
import com.mgtu.museum.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    UserService userService;
    ModelMapper mapper;

    @PostMapping("create")
    public Response<Object> createUser(String principal, @RequestBody CreateUserDto dto){
        userService.createUser(dto);
        return Response.builder()
                .message("Пользователь успешно создан")
                .status(HttpStatus.OK).build();
    }
}
