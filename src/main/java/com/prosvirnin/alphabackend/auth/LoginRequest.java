package com.prosvirnin.alphabackend.auth;

import com.prosvirnin.alphabackend.model.user.User;

public record LoginRequest(String email, String fullName, String password) {

    public User toEntity() {
        var user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        return user;
    }

}
