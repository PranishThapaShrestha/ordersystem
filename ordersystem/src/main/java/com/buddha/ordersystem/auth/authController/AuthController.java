package com.buddha.ordersystem.auth.authController;

import com.buddha.ordersystem.auth.authDTO.AuthResponse;
import com.buddha.ordersystem.auth.authService.AuthenticationService;
import com.buddha.ordersystem.auth.authDTO.LoginRequest;
import com.buddha.ordersystem.auth.authService.RefreshTokenService;
import com.buddha.ordersystem.auth.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    public ResponseEntity<AuthResponse> refresh(@RequestParam String refreshToken){
        RefreshToken token= refreshTokenService.verifyExpiration(refreshTokenService.findByToken(refreshToken));
    }

}
