package org.example.FinalProject.dto;

import lombok.Data;
import java.util.List;

@Data
public class PersonInfoDTO {
    private List<String> firstName;
    private List<String> lastName;
    private List<String> address;
    private List<Integer> age;
    private List<String> email;
    private List<String> medicationDosage;
    private List<String> allergies;
}
