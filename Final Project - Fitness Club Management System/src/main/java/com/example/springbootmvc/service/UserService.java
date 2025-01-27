package com.example.springbootmvc.service;

import com.example.springbootmvc.model.User;
import com.example.springbootmvc.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean deleteUserByUsername(User temp){
        User user = userRepository.findByUsername(temp.getUsername());
        if(user != null && !user.getRole().equals("Role_Admin")){
            userRepository.deleteById(user.getId());
            return true;
        }
        return false;
    }
}

