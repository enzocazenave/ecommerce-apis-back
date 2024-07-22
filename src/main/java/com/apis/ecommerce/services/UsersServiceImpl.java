package com.apis.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apis.ecommerce.entities.User;
import com.apis.ecommerce.exceptions.UserDuplicateException;
import com.apis.ecommerce.repositories.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public User registerUser(String name, String surname, String email, String password) throws UserDuplicateException {
        if (!usersRepository.findByEmail(email).isEmpty()) {
            throw new UserDuplicateException();
        }
        
        User user = new User(name, surname, email, password);
        return usersRepository.save(user);
    }

    public User loginUser(String email, String password) {
        return null;
    }
}