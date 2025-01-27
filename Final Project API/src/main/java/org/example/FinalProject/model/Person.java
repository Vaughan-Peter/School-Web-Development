package org.example.FinalProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @NotNull(message = "First name must not be null")
    String firstName;

    @NotNull(message = "Last name must not be null")
    String lastName;

    @NotNull(message = "E-mail must not be null")
    String email;

    @NotNull(message = "Address must not be null")
    String address;

    @NotNull(message = "City must not be null")
    String city;

    @NotNull(message = "Phone number must not be null")
    String phoneNumber;

    @NotNull(message = "Age number must not be null")
    @Min(value = 0, message = "Age must be a positive number!")
    int age;

    @NotNull(message = "Medication and dosage must not be null")
    String medicationDosage;

    @NotNull(message = "Allergies must not be null")
    String allergies;

    @NotNull(message = "Fire station must not be null")
    @Min(value = 0, message = "Fire Station Number must be a positive number!")
    int fireStation;

    @Id
    @GeneratedValue
    private Long id;

    public Person(String firstName, String lastName, String email, String address, String city, String phoneNumber,
                  int age, String medicationDosage, String allergies, int fireStation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.medicationDosage = medicationDosage;
        this.allergies = allergies;
        this.fireStation = fireStation;
    }
}
