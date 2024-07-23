package com.apis.ecommerce.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apis.ecommerce.entities.User;
import com.apis.ecommerce.entities.dto.UserLoginRequest;
import com.apis.ecommerce.entities.dto.UserRequest;
import com.apis.ecommerce.entities.dto.UserResponse;
import com.apis.ecommerce.exceptions.UserDuplicateException;
import com.apis.ecommerce.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) throws UserDuplicateException {
        User user = usersService.registerUser(userRequest.getName(), userRequest.getSurname(), userRequest.getEmail(), userRequest.getPassword());
        UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.isAdmin());
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        Optional<User> optionalUser = usersService.loginUser(userLoginRequest.getEmail(), userLoginRequest.getPassword());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.isAdmin());
            return ResponseEntity.ok(userResponse);
        }

        return ResponseEntity.status(401).build();
    }
}