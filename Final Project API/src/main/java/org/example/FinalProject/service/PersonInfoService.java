package org.example.FinalProject.service;

import org.example.FinalProject.model.Person;
import org.example.FinalProject.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonInfoService {
    private final PersonRepository personRepository;
    public PersonInfoService(PersonRepository personRepository){this.personRepository = personRepository;}

    public Map<String, Object> getPeopleByInfo(String firstName, String lastName){
        List<Person> people = personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
        //List<Person> people = null;
        if(people.isEmpty()){
            return Collections.emptyMap();
        }

        List<Map<String, String>> personInfo = new ArrayList<>();

        for(Person person : people){
            Map<String, String> personData = new HashMap<>();
            personData.put("firstName", person.getFirstName());
            personData.put("lastName", person.getLastName());
            personData.put("address", person.getAddress());
            personData.put("age", String.valueOf(person.getAge()));
            personData.put("email", person.getEmail());
            personData.put("Medication and dosage", person.getMedicationDosage());
            personInfo.add(personData);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("people", personInfo);
        return response;
    }
}
