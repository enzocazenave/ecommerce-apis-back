package com.apis.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.apis.ecommerce.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public Optional<User> loginUser(String email, String password) {
        List<User> user = usersRepository.findByEmail(email);

        if (user.isEmpty()) {
            return Optional.empty();
        }

        if (user.get(0).getPassword().equals(password)) {
            return Optional.of(user.get(0));
        }

        return Optional.empty();
    }

    public Optional<User> deleteUser(Long id) {
        Optional<User> user = usersRepository.findById(id);

        if (!user.isPresent()) {
            return Optional.empty();
        }

        user.get().setStatus(false);
        usersRepository.save(user.get());

        return user;
    }

    public Optional<User> getUserById(Long id) {
        Optional<User> user = usersRepository.findById(id);

        if (!user.isPresent()) {
            return Optional.empty();
        }

        return user;
    }
}