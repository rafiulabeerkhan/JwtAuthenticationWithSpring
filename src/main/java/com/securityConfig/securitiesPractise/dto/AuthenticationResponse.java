package com.securityConfig.securitiesPractise.dto;

import com.securityConfig.securitiesPractise.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private UserRole userRole;

}
