package com.mgtu.museum.controller.UserController.dto;

import com.mgtu.museum.Enum.UserRole;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {
    private String username;
    private String secret;
    private String name;
    private String middleName;
    private String lastName;
    private UserRole role;
}
