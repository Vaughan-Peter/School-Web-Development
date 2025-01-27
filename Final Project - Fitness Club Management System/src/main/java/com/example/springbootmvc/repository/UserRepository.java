package com.example.springbootmvc.repository;

import com.example.springbootmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User findByUsername(String username);
    boolean existsByRole(String role);


}
