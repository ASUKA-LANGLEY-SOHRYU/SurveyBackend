package com.prosvirnin.alphabackend.config;

import com.prosvirnin.alphabackend.auth.AuthenticationResponse;
import com.prosvirnin.alphabackend.exception.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<AuthenticationResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        String errorMessage = "Email already exists: " + ex.getMessage();
        AuthenticationResponse response = new AuthenticationResponse();
        response.setError(errorMessage);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<AuthenticationResponse> handleInvalidUsernameOrPasswordException(AuthenticationException ex) {
        String errorMessage = "Invalid username or password: " + ex.getMessage();
        AuthenticationResponse response = new AuthenticationResponse();
        response.setError(errorMessage);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
