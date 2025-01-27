package com.example.springbootmvc.model;

import com.example.springbootmvc.dto.ScheduleDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue()
    private Long id;

    @ManyToOne
    User user;
    String sunday;
    String monday;
    String tuesday;
    String wednesday;
    String thursday;
    String friday;
    String saturday;

    public Schedule(ScheduleDTO scheduleDTO, User user){
        this.user = user;
        this.sunday = scheduleDTO.getSunday();
        this.monday = scheduleDTO.getMonday();
        this.tuesday = scheduleDTO.getTuesday();
        this.wednesday = scheduleDTO.getWednesday();
        this.thursday = scheduleDTO.getThursday();
        this.friday = scheduleDTO.getFriday();
        this.saturday = scheduleDTO.getSaturday();
    }
}
