package com.securityConfig.securitiesPractise.services.auth;

import com.securityConfig.securitiesPractise.dto.SignUpRequest;
import com.securityConfig.securitiesPractise.dto.UserDto;

public interface AuthService {
    UserDto signupUser(SignUpRequest signUpRequest);

    boolean hasUserWithEmail(String email);
}
