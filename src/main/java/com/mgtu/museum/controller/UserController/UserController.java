package com.mgtu.museum.controller.UserController;

import com.mgtu.museum.controller.UserController.request.CreateUserRequest;
import com.mgtu.museum.controller.UserController.request.ResetPasswordRequest;
import com.mgtu.museum.controller.UserController.request.UpdateUserRequest;
import com.mgtu.museum.controller.UserController.response.GetUserResponse;
import com.mgtu.museum.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.nio.file.ProviderNotFoundException;
import java.util.List;

@RestController()
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    UserService userService;
    ModelMapper mapper;

    @PostMapping("create")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest dto) {
        userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь создан");
    }
    @PostMapping("change_password")
    public ResponseEntity<String> changePassword(@RequestBody ResetPasswordRequest dto) throws AccessDeniedException {
        userService.changePassword(dto);
        return ResponseEntity.ok("Пароль успешно изменен");
    }

    @PutMapping("update_user")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest dto){
        userService.updateUser(dto);
        return ResponseEntity.ok("пользователь обновлен");
    }
    @GetMapping("getAll")
    public ResponseEntity<List<GetUserResponse>> getUsers(){
        return ResponseEntity.ok(userService.getAll());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.ok("Пользователь удален");
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getById(id));
    }
}
