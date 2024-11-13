package com.mgtu.museum.controller.Auth;

import com.mgtu.museum.controller.Auth.request.SignInRequest;
import com.mgtu.museum.controller.Auth.response.SignInResponse;
import com.mgtu.museum.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInDto) throws LoginException {
        try {
            return ResponseEntity.ok(authService.signIn(signInDto));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new SignInResponse(e.getMessage()));
        }
    }
}
