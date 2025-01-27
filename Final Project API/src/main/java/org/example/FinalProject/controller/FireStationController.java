package org.example.FinalProject.controller;

import lombok.extern.log4j.Log4j2;
import org.example.FinalProject.model.Person;
import org.example.FinalProject.repository.PersonRepository;
import org.example.FinalProject.service.FireStationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
public class FireStationController {
    private final PersonRepository personRepository;
    private final FireStationService fireStationService;

    public FireStationController(PersonRepository personRepository, FireStationService fireStationService) {
        this.personRepository = personRepository;
        this.fireStationService = fireStationService;
    }

    @PostMapping("/firestation")
    public ResponseEntity<Person> addFireStation(@RequestBody Person person){
        personRepository.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @GetMapping(value = "/firestation/all")
    public ResponseEntity<List<Person>> listFireStation(){
        List<Person> people = personRepository.findAll();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    //GetByID Request
    @GetMapping(value = "/firestation/{id}")
    public ResponseEntity<Person> listSingleFireStations(@PathVariable Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found"));
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    //GetByFireStation Request
    @GetMapping(value = "/firestation")
    public ResponseEntity<Map<String, Object>> oneFireStation(@RequestParam("stationNumber") int fireStation){
        Map<String, Object> response = fireStationService.getPeopleByStation(fireStation);
        if(response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(response);
    }
}
