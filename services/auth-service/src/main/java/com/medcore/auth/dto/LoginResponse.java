package com.medcore.auth.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LoginResponse {

    private String accessToken;

    private String tokenType;

    private Long expiresIn;

    private String username;

    private List<String> roles;

}