package com.prosvirnin.alphabackend.auth;

import com.prosvirnin.alphabackend.config.JwtService;
import com.prosvirnin.alphabackend.exception.EmailAlreadyExistsException;
import com.prosvirnin.alphabackend.model.user.Role;
import com.prosvirnin.alphabackend.model.user.User;
import com.prosvirnin.alphabackend.repository.UserRepository;
import com.prosvirnin.alphabackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(LoginRequest request){
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }

        var user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();// new User(request.email(), passwordEncoder.encode(request.password()), request.fullName());
        user.setRole(Role.User);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        var authResponse =  new AuthenticationResponse();
        authResponse.setToken(jwtToken);
        return authResponse;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var user = userRepository.findByEmail(request.email()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var authResponse =  new AuthenticationResponse();
        authResponse.setToken(jwtToken);
        return authResponse;
    }
}

