package com.apis.ecommerce.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apis.ecommerce.entities.User;
import com.apis.ecommerce.exceptions.UserDuplicateException;

@Service
public interface UsersService {
    public User registerUser(String name, String surname, String email, String password) throws UserDuplicateException;
    public Optional<User> loginUser(String email, String password);
    public Optional<User> deleteUser(Long id);
    public Optional<User> getUserById(Long id);
}