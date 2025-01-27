package org.example.FinalProject.dto;

import lombok.Data;
import java.util.List;

@Data
public class FireStationDTO {
    private List<String> firstName;
    private List<String> lastName;
    private List<String> address;
    private List<Integer> fireStation;
    private List<Integer> age;
}
