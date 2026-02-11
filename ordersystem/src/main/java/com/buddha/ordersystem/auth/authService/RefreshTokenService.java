package com.buddha.ordersystem.auth.authService;

import com.buddha.ordersystem.auth.AuthRepository.RefreshTokenRepository;
import com.buddha.ordersystem.auth.authDTO.AuthResponse;
import com.buddha.ordersystem.auth.authDTO.TokenRefreshResponse;
import com.buddha.ordersystem.auth.entity.RefreshToken;
import com.buddha.ordersystem.entity.Users;
import com.buddha.ordersystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public RefreshToken createRefreshToken(String email) {

        Users user = userRepository.findBdByEmail(email).orElseThrow();

        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plus(7, ChronoUnit.DAYS));

        return refreshTokenRepository.save(token);

    }


    public RefreshToken verifyExpiration(RefreshToken token) {


        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired");
        }
        return token;
    }

    public AuthResponse findByToken(String refreshToken) {

        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if(token.isRevoked()|| token.getExpiryDate().isBefore(Instant.now())){
            throw new
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(token.getUser().getEmail());

        String accessToken= jwtService.generateAccessToken(userDetails);



       return RefreshToken(accessToken,.)

    }


}
