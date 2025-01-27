package com.example.springbootmvc.controller;

import com.example.springbootmvc.dto.ScheduleDTO;
import com.example.springbootmvc.model.Schedule;
import com.example.springbootmvc.repository.ScheduleRepository;
import com.example.springbootmvc.service.UserService;
import org.springframework.ui.Model;
import com.example.springbootmvc.model.User;
import com.example.springbootmvc.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
** Admin Controller for main page, modifying trainer schedules, modifying users and delete users.
* Some of the admin logic such as creating is in CreateUserController
*/
@Controller
public class AdminController {

    final UserRepository userRepository;
    final UserService userService;
    final ScheduleRepository scheduleRepository;

    public AdminController(UserRepository userRepository, UserService userService,
                           ScheduleRepository scheduleRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping("/adminMainPage")
    public String adminPage(){
        return "adminMainPage";
    }

    @GetMapping("/adminModifyTrainer")
    public String trainerSchedulePage(){
        return "adminModTrainSched";
    }

    @PostMapping("/adminModifyTrainer")
    public String trainModPage(@ModelAttribute ScheduleDTO scheduleDTO){
        User user = userRepository.findByUsername(scheduleDTO.getUserName());
        Schedule schedule = new Schedule(scheduleDTO, user);
        scheduleRepository.save(schedule);
        return "adminMainPage";
    }

    @GetMapping("/adminModifyUsers")
    public String userModificationPage(){
        return "adminModUsers";
    }

    @PostMapping("/adminModifyUsers")
    public String modifyUsers(@ModelAttribute User user, Model model){
        User oldUser= userRepository.findByUsername(user.getUsername());
        if(oldUser == null){
            model.addAttribute("error", "Username does not exist.");
            return "genericFailure";
        }
        if(oldUser.getRole().equals("Role_Admin")){
            model.addAttribute("error", "This is an admin account.");
            return "genericFailure";
        }
        user.setId(oldUser.getId());
        userRepository.save(user);
        return "adminMainPage";
    }

    @GetMapping("/adminDeleteUsers")
    public String userDeletingPage(){
        return "adminDeleteUsers";
    }

    @PostMapping("/adminDeleteUsers")
    public String deleteByUsername(@ModelAttribute User user, Model model){
        User newUser= userRepository.findByUsername(user.getUsername());
        if(newUser == null){
            model.addAttribute("error", "Username does not exist.");
            return "genericFailure";
        }
        if(newUser.getRole().equals("Role_Admin")){
            model.addAttribute("error", "This is an admin account.");
            return "genericFailure";
        }
        userRepository.deleteById(newUser.getId());
        return "adminMainPage";
    }
}
