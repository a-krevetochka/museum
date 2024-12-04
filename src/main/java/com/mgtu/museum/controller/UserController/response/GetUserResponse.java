package com.mgtu.museum.controller.UserController.response;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GetUserResponse {
    Integer id;
    String name;
    String middle_name;
    String last_name;
    String role;
}
