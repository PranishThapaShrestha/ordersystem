package com.buddha.ordersystem.auth.authService;

import com.buddha.ordersystem.auth.AuthRepository.RefreshTokenRepository;
import com.buddha.ordersystem.auth.authDTO.AuthResponse;
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

    public RefreshToken createRefreshToken(Users user) {

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

    public AuthResponse refreshToken(String refreshToken) {

        RefreshToken oldToken = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if (oldToken.isRevoked() || oldToken.getExpiryDate().isBefore(Instant.now())) {
            throw new RuntimeException(refreshToken);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(oldToken.getUser().getEmail());

        String newAccessToken = jwtService.generateAccessToken(userDetails);


        RefreshToken refreshToken1 = refreshTokenService.createRefreshToken(oldToken.getUser());

        return new AuthResponse(newAccessToken, refreshToken1.getToken());

    }


    public RefreshToken findbyToken(String refreshToken) {

        return refreshTokenRepository.findByToken(refreshToken)
                        .orElseThrow(() -> new RuntimeException("Refresh Token not found!!!"));

    }
}
