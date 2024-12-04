package com.mgtu.museum.controller.UserController.request;

import com.mgtu.museum.Enum.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @NonNull
    Integer userId;
    String name;
    String middleName;
    String lastName;
    UserRole role;
}
