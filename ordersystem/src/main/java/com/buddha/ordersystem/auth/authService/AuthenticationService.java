package com.buddha.ordersystem.auth.authService;

import com.buddha.ordersystem.auth.authDTO.AuthResponse;
import com.buddha.ordersystem.auth.authDTO.LoginRequest;
import com.buddha.ordersystem.auth.entity.RefreshToken;
import com.buddha.ordersystem.entity.Users;
import com.buddha.ordersystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    public AuthResponse login(LoginRequest loginRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()));

            Users user = userRepository.findBdByEmail(loginRequest.getEmail()).orElseThrow();
            user.setFailedAttempts(0);
            userRepository.save(user);

        } catch (BadCredentialsException exception) {
            Users user = userRepository.findBdByEmail(loginRequest.getEmail()).orElseThrow();
            user.setFailedAttempts(user.getFailedAttempts() + 1);
            userRepository.save(user);

            if (user.getFailedAttempts() > 5) {
                user.setAccountNonlocked(false);
            }
            userRepository.save(user);
            throw exception;
        }
        Users user = userRepository.findBdByEmail(loginRequest.getEmail()).orElseThrow();

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String accessTokenString = jwtService.generateAccessToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);


        return new AuthResponse(accessTokenString,refreshToken.getToken() );
    }
}
