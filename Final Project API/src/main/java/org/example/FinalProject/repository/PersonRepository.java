package org.example.FinalProject.repository;

import org.example.FinalProject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPeopleByCity(String city);
    List<Person> findPeopleByFireStation(int fireStation);
    List<Person> findChildByAddress(String address);
    List<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);
}
