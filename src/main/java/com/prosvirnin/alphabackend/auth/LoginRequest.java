package com.prosvirnin.alphabackend.auth;

import com.prosvirnin.alphabackend.model.user.User;

public record LoginRequest(String email, String password) {
}
