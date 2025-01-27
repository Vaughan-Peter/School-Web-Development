package com.example.springbootmvc.controller;

import com.example.springbootmvc.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Main page
@Controller
public class MainPageController {

   final UserRepository userRepository;

    public MainPageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/mainPage")
    public String mainPage(){return "mainPage";}
}
