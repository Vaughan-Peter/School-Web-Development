package com.example.springbootmvc.controller;

import com.example.springbootmvc.dto.AttendanceDTO;
import com.example.springbootmvc.model.Attendance;
import com.example.springbootmvc.model.Schedule;
import com.example.springbootmvc.model.User;
import com.example.springbootmvc.repository.AttendanceRepository;
import com.example.springbootmvc.repository.ScheduleRepository;
import com.example.springbootmvc.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/*
** Trainers main page, gym schedule for trainers and attendance for members.
*/
@Controller
public class TrainerController {

    final ScheduleRepository scheduleRepository;
    final AttendanceRepository attendanceRepository;
    final UserRepository userRepository;

    public TrainerController(ScheduleRepository scheduleRepository, AttendanceRepository attendanceRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/trainerMainPage")
    public String trainerPage(){
        return "trainerMainPage";
    }

    @GetMapping("/trainerAttendance")
    public String attendancePage(){return "trainerAttendance";}

    @PostMapping("/trainerAttendance")
    public String attendanceModPage(@ModelAttribute AttendanceDTO attendanceDTO){
        User user = userRepository.findByUsername(attendanceDTO.getUserName());
        Attendance attendance = new Attendance(attendanceDTO, user);
        attendanceRepository.save(attendance);
        return "trainerMainPage";
    }

    @GetMapping("/trainerInfo")
    public String infoPage(){
        return "trainerInfo";
    }

    @GetMapping("/trainerSchedule")
    public String schedulePage(Model model){
        List<Schedule> schedule = scheduleRepository.findAll();
        model.addAttribute("schedule", schedule);
        return "trainerSchedule";
    }
}
