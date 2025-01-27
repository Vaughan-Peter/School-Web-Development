package org.example.FinalProject.controller;

import lombok.extern.log4j.Log4j2;
import org.example.FinalProject.model.Person;
import org.example.FinalProject.repository.PersonRepository;
import org.example.FinalProject.service.ChildAlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
public class ChildAlertController {

    private final PersonRepository personRepository;
    private final ChildAlertService childAlertService;

    public ChildAlertController(PersonRepository personRepository, ChildAlertService childAlertService) {
        this.personRepository = personRepository;
        this.childAlertService = childAlertService;
    }

    @PostMapping("/childAlert")
    public ResponseEntity<Person> addChildAlert(@RequestBody Person person){
        personRepository.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @GetMapping(value = "/childAlert/all")
    public ResponseEntity<List<Person>> listChildAlert(){
        List<Person> people = personRepository.findAll();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    //GetByID Request
    @GetMapping(value = "/childAlert/{id}")
    public ResponseEntity<Person> singleChildAlert(@PathVariable Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found"));
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    //GetByAddress Request
    @GetMapping(value = "/childAlert")
    public ResponseEntity<Map<String, Object>> childByAddress(@RequestParam("address") String personAddress){
        Map<String, Object> response = childAlertService.getChildByAddress(personAddress);
        if(response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }
}
