package com.prosvirnin.alphabackend.model.user;

public record LoginRequest(String email, String fullName, String password) {
}
