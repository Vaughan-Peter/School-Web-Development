package com.example.springbootmvc.repository;

import com.example.springbootmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<User, Long> {
}
