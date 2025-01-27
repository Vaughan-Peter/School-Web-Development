package com.example.springbootmvc.controller;

import com.example.springbootmvc.model.User;
import com.example.springbootmvc.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
** Log in controller for all users: members, trainers and admin.
** This could be expended if there are different types of members.
*/
@Controller
public class LoginController {

    final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/memberLogin")
    public String LogginOnMember(){
        return "memberLogin";
    }

    @PostMapping("/memberLogin")
    public String memberLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                              HttpSession session, Model model){
        if(session.getAttribute("user") != null){
            model.addAttribute("error","You are already signed in!");
            return "genericFailure";
        }

        User user = userRepository.findByUsername(username);
        if(user != null && password.equals(user.getPassword())&&user.getRole().equals("Role_Member")){
            session.setAttribute("user", user);
            return "redirect:/memberInfo";
        }else{
            model.addAttribute("error", "Invalid username or password or not correct login area!");
            return "genericFailure";
        }
    }

    @GetMapping("/trainer")
    public String LogginOnTrainer(){
        return "trainerLogin";
    }

    @PostMapping("/trainer")
    public String trainerLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model){
        if(session.getAttribute("user") != null){
            model.addAttribute("error","You are already signed in!");
            return "genericFailure";
        }

        User user = userRepository.findByUsername(username);
        if(user != null && password.equals(user.getPassword())&&user.getRole().equals("Role_Trainer")){
            session.setAttribute("user", user);
            return "trainerMainPage";
        }else{
            model.addAttribute("error", "Invalid username or password or not correct login area!");
            return "genericFailure";
        }
    }

    @GetMapping("/admin")
    public String LogginOnAdmin(){
        return "adminLogin";
    }

    @PostMapping("/admin")
    public String adminLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model){

        if(session.getAttribute("user") != null){
            model.addAttribute("error","You are already signed in!");
            return "genericFailure";
        }

        User user = userRepository.findByUsername(username);
        if(user != null && password.equals(user.getPassword())&&user.getRole().equals("Role_Admin")){
            session.setAttribute("user", user);
            return "adminMainPage";
        }else{
            model.addAttribute("error",
                    "Invalid username or password or not correct login area!");
            return "genericFailure";
        }
    }
}
