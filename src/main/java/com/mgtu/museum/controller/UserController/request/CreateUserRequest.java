package com.mgtu.museum.controller.UserController.request;

import com.mgtu.museum.Enum.UserRole;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    private String username;
    private String secret;
    private String name;
    private String middleName;
    private String lastName;
    private UserRole role;
}
