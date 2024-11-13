package com.mgtu.museum.controller.Auth.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInRequest {
    String username;
    String secret;
}
