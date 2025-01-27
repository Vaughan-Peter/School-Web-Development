package org.example.FinalProject.service;

import org.example.FinalProject.model.Person;
import org.example.FinalProject.repository.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FireStationService {

    private final PersonRepository personRepository;
    public FireStationService(PersonRepository personRepository){this.personRepository = personRepository;}

    public Map<String, Object> getPeopleByStation(int stationNumber){
        List<Person> people = personRepository.findPeopleByFireStation(stationNumber);

        if(people.isEmpty()){
            return Collections.emptyMap();
        }

        int adultCount = 0;
        int childCount = 0;
        List<Map<String, String>> personInfo = new ArrayList<>();

        for(Person person : people){
            Map<String, String> personData = new HashMap<>();
            personData.put("firstName", person.getFirstName());
            personData.put("lastName", person.getLastName());
            personData.put("address", person.getAddress());
            personData.put("phone", person.getPhoneNumber());

            if(person.getAge() > 18){
                adultCount++;
            } else {
                childCount++;
            }
            personInfo.add(personData);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("people", personInfo);
        response.put("adultCount", adultCount);
        response.put("childCount", childCount);
        return response;
    }
}
