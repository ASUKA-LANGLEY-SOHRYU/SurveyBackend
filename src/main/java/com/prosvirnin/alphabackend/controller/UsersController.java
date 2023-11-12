package com.prosvirnin.alphabackend.controller;

import com.prosvirnin.alphabackend.model.user.User;
import com.prosvirnin.alphabackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id){
        return usersService.findById(id);
    }

    @PostMapping("/{id}/edit")
    public ResponseEntity<?> editUser(@PathVariable("id") Long id, @RequestBody User user){
        if(usersService.edit(id, user))
            return ResponseEntity.ok(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
