package com.example.springbootmvc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.example.springbootmvc.model.User;
import com.example.springbootmvc.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
** Logging out for all users is here.
** Rest of admin controls: Creating users with password and email error-handling.
 */
@Controller
public class CreateUserController {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[^.]+(.[A-Za-z0-9-]+)+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    final UserRepository userRepository;

    public CreateUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/success")
    public String logoutNow(){
        return "logoutSuccessful";
    }

    @GetMapping("/logout")
    public String loginComplete(HttpSession session, Model model) {
        if (session == null || session.getAttribute("user") == null){
            model.addAttribute("error", "You are not signed in");
            return "genericFailure";
        }
        session.invalidate();
        return "logoutSuccessful";
    }

    @PostMapping("/adminAddUser")
    public String createUser(@RequestParam("name") String name, @RequestParam("username") String username,
                             @RequestParam("email") String email, @RequestParam("password") String password,
                             @RequestParam("role") String role, Model model){

        if(userRepository.existsByUsername(username)||userRepository.existsByEmail(email)) {
            model.addAttribute("error", "The Account already exists!");
            return "genericFailure";
        }

        Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
        if(!emailMatcher.matches()){
            model.addAttribute("error","Not a valid email format!");
            return "genericFailure";
        }

        Matcher passwordMatcher = PASSWORD_PATTERN.matcher(password);
        if(!passwordMatcher.matches()){
            model.addAttribute("error", "A password must be at least 8 characters long, " +
                    "have an upper and lower case letter, number and special character");
            return "genericFailure";
        }

        User user = new User(name, username, email, password, role);

        if (user.getRole().equals("Role_Admin")) {
            model.addAttribute("error", "You cannot be an admin");
            return "genericFailure";
        }
        else{
            user = new User(name, username, email, password, role);
        }

        userRepository.save(user);
        return "adminMainPage";
    }
}