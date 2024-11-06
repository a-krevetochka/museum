package com.mgtu.museum.controller.Auth;

import com.mgtu.museum.controller.Auth.Dto.SignInDto;
import com.mgtu.museum.controller.Response;
import com.mgtu.museum.service.AuthService;
import com.mgtu.museum.service.TokenService;
import com.mgtu.museum.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public Response<String> signIn(@RequestBody SignInDto signInDto) throws LoginException {
        String token = authService.signIn(signInDto);
        return Response.<String>builder()
                .data(token)
                .build();
    }
}
