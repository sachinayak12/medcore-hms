package com.medcore.auth.service;

import com.medcore.auth.dto.LoginRequest;
import com.medcore.auth.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}