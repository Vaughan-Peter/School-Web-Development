package com.example.springbootmvc.controller;

import com.example.springbootmvc.model.Attendance;
import com.example.springbootmvc.repository.AttendanceRepository;
import com.example.springbootmvc.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import com.example.springbootmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

/*
** Membership Controller - not much to control but to requirement.
*/
@Controller
public class MemberController {

    final UserRepository userRepository;
    final AttendanceRepository attendanceRepository;

    public MemberController(UserRepository userRepository, AttendanceRepository attendanceRepository) {
        this.userRepository = userRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @GetMapping("/memberInfo")
    public String adminPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        //List<Attendance> attendance = attendanceRepository.findAll();
        List<Attendance> attendance = attendanceRepository.findByUser(user);
        model.addAttribute("attendance", attendance);
        return "memberInfo";
    }
}
