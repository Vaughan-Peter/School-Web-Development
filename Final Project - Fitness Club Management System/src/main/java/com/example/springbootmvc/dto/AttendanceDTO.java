package com.example.springbootmvc.dto;

import lombok.Data;

@Data
public class AttendanceDTO {
    String userName;
    int sundayCount;
    int mondayCount;
    int tuesdayCount;
    int wednesdayCount;
    int thursdayCount;
    int fridayCount;
    int saturdayCount;
}
