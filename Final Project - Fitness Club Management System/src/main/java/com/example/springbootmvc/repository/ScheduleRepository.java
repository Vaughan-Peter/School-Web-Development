package com.example.springbootmvc.repository;

import com.example.springbootmvc.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
