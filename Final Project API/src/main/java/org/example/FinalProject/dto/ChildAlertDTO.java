package org.example.FinalProject.dto;

import lombok.Data;
import java.util.List;

@Data
public class ChildAlertDTO {
    private List<String> firstName;
    private List<String> lastName;
    private List<String> address;
    private List<Integer> age;
}
