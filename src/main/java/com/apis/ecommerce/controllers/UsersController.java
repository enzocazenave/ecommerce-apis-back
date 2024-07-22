package com.apis.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apis.ecommerce.entities.User;
import com.apis.ecommerce.entities.dto.UserRequest;
import com.apis.ecommerce.exceptions.UserDuplicateException;
import com.apis.ecommerce.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserRequest userRequest) throws UserDuplicateException {
        User user = usersService.registerUser(userRequest.getName(), userRequest.getSurname(), userRequest.getEmail(), userRequest.getPassword());
        return ResponseEntity.ok(user);
    }
}