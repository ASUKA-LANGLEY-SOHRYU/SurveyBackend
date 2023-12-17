package com.prosvirnin.alphabackend.controller;

import com.prosvirnin.alphabackend.auth.LoginRequest;
import com.prosvirnin.alphabackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {

    private final UsersService usersService;

    @Autowired
    public MainController( UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody LoginRequest loginRequest) {
        usersService.save(loginRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}