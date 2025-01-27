package com.example.springbootmvc.repository;

import com.example.springbootmvc.model.Attendance;
import com.example.springbootmvc.model.Schedule;
import com.example.springbootmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByUser(User user);
}
