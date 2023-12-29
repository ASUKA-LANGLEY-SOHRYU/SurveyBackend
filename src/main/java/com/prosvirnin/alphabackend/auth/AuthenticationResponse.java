package com.prosvirnin.alphabackend.auth;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String token;
    private String error;
}

