package org.example.FinalProject.controller;

import lombok.extern.log4j.Log4j2;
import org.example.FinalProject.model.Person;
import org.example.FinalProject.repository.PersonRepository;
import org.example.FinalProject.service.PersonInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
public class PersonInfoController {

    private final PersonRepository personRepository;
    private final PersonInfoService personInfoService;

    public PersonInfoController(PersonRepository personRepository, PersonInfoService personInfoService) {
        this.personRepository = personRepository;
        this.personInfoService = personInfoService;
    }

    @PostMapping("/personInfo")
    public ResponseEntity<Person> addPersonInfo(@RequestBody Person person){
        personRepository.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @GetMapping(value = "/personInfo/all")
    public ResponseEntity<List<Person>> listPersonInfo(){
        List<Person> people = personRepository.findAll();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    //GetByID Request
    @GetMapping(value = "/personInfo/{id}")
    public ResponseEntity<Person> singlePerson(@PathVariable Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found"));
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    //GetByAddress Request
    @GetMapping(value = "/personInfo")
    public ResponseEntity<Map<String, Object>> searchPersonInfo(@RequestParam("firstName") String firstName,
                                                                @RequestParam("lastName") String lastName){
        Map<String, Object> response = personInfoService.getPeopleByInfo(firstName, lastName);
        if(response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }
}
