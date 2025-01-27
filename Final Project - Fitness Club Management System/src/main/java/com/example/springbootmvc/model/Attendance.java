package com.example.springbootmvc.model;

import com.example.springbootmvc.dto.AttendanceDTO;
import com.example.springbootmvc.dto.ScheduleDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attendance {

    @Id
    @GeneratedValue()
    private Long id;

    @ManyToOne
    User user;

    @Min(value = 0, message = "This must be a positive number!")
    int sundayCount;
    @Min(value = 0, message = "This must be a positive number!")
    int mondayCount;
    @Min(value = 0, message = "This must be a positive number!")
    int tuesdayCount;
    @Min(value = 0, message = "This must be a positive number!")
    int wednesdayCount;
    @Min(value = 0, message = "This must be a positive number!")
    int thursdayCount;
    @Min(value = 0, message = "This must be a positive number!")
    int fridayCount;
    @Min(value = 0, message = "This must be a positive number!")
    int saturdayCount;

    public Attendance(AttendanceDTO attendanceDTO, User user) {
        this.user = user;
        this.sundayCount = attendanceDTO.getSundayCount();
        this.mondayCount = attendanceDTO.getMondayCount();
        this.tuesdayCount = attendanceDTO.getTuesdayCount();
        this.wednesdayCount = attendanceDTO.getWednesdayCount();
        this.thursdayCount = attendanceDTO.getThursdayCount();
        this.fridayCount = attendanceDTO.getFridayCount();
        this.saturdayCount = attendanceDTO.getSaturdayCount();
    }
}