package com.medcore.auth.service.impl;

import com.medcore.auth.dto.LoginRequest;
import com.medcore.auth.dto.LoginResponse;
import com.medcore.auth.entity.User;
import com.medcore.auth.repository.UserRepository;
import com.medcore.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {

            throw new RuntimeException("Invalid password");
        }

        return LoginResponse.builder()
                .username(user.getUsername())
                .tokenType("Bearer")
                .expiresIn(1800L)
                .accessToken("JWT_WILL_COME_HERE")
                .build();
    }
}