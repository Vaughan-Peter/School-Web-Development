package org.example.FinalProject.service;

import org.example.FinalProject.model.Person;
import org.example.FinalProject.repository.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ChildAlertService {

    private final PersonRepository personRepository;
    public ChildAlertService(PersonRepository personRepository){this.personRepository = personRepository;}

    public Map<String, Object> getChildByAddress(String personAddress){
        List<Person> people = personRepository.findChildByAddress(personAddress);

        if(people.isEmpty()){
            return Collections.emptyMap();
        }

        int adultCountTwo = 0;
        int childCountTwo = 0;
        List<Map<String, String>> personAddressInfo = new ArrayList<>();

        for(Person person : people){
            Map<String, String> personData = new HashMap<>();
            personData.put("firstName", person.getFirstName());
            personData.put("lastName", person.getLastName());
            personData.put("address", person.getAddress());
            personData.put("age", String.valueOf(person.getAge()));

            if(person.getAge() > 18){
                adultCountTwo++;
            } else {
                childCountTwo++;
            }
            personAddressInfo.add(personData);
        }

        if(childCountTwo > 0) {
            Map<String, Object> response = new HashMap<>();
            response.put("people", personAddressInfo);
            response.put("adultCount", adultCountTwo);
            response.put("childCount", childCountTwo);
            return response;
        }
        Map<String, Object> response = new HashMap<>();
        response.put("No Children Here", childCountTwo);
        return response;
    }
}
