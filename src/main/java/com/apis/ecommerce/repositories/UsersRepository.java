package com.apis.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apis.ecommerce.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.status = true")
    List<User> findByEmail(String email);
    
    @SuppressWarnings("null")
    @Query("SELECT u FROM User u WHERE u.id = ?1 AND u.status = true")
    Optional<User> findById(Long id);
}