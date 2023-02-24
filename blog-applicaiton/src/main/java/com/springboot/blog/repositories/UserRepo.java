package com.springboot.blog.repositories;

import com.springboot.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String name, String email);

    boolean existsByUsername(String name);
//
    boolean existsByEmail(String email);
}