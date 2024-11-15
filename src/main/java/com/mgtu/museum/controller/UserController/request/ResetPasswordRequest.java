package com.mgtu.museum.controller.UserController.request;

import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Getter
@Setter
public class ResetPasswordRequest {
    private String username;
    private String previousPassword;
    private String newPassword;
}
