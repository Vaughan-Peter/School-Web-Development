package com.example.springbootmvc.Util;

import com.example.springbootmvc.model.Admin;
import com.example.springbootmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminCreation {

    private final UserRepository userRepository;

    public AdminCreation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void initAdmin(){
        if (!userRepository.existsByRole("Role_Admin")){
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("a");
            admin.setRole("Role_Admin");
            userRepository.save(admin);
        }
    }
}
