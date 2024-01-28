package com.paskhinproject.HomeWorkAstonProject.controllers;


import com.paskhinproject.HomeWorkAstonProject.dto.NewUserPasswordDto;
import com.paskhinproject.HomeWorkAstonProject.dto.RegisterLoginUserDto;
import com.paskhinproject.HomeWorkAstonProject.models.AppUser;
import com.paskhinproject.HomeWorkAstonProject.services.AppUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")

@RequiredArgsConstructor
public class AppUserController {

    private final AppUserServiceImpl userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody RegisterLoginUserDto newUser) throws LoginException {
            userService.registerNewUser(newUser);
            return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterLoginUserDto userDto) throws FailedLoginException {
        if (!userService.login(userDto.getUsername(), userDto.getPassword())) {
            return new ResponseEntity<>("Username or password not found.", HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>("Logged in successfully.", HttpStatus.ACCEPTED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> usersList = userService.getListOfAllUsers();
        return ResponseEntity.ok(usersList);
    }

    @PutMapping("/change_password")
    public ResponseEntity<AppUser> changePassword(@RequestBody NewUserPasswordDto newPasswordDto) {
        if (userService.changePassword(newPasswordDto)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
