package org.example.FinalProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.example.FinalProject.dto.CommunityEmailDTO;
import org.example.FinalProject.model.Person;
import org.example.FinalProject.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Log4j2
public class CommunityEmailController {

    private final PersonRepository personRepository;

    public CommunityEmailController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/communityEmail")
    public ResponseEntity<Person> addInfo(@RequestBody Person person){
        personRepository.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @GetMapping(value = "/communityEmail/all")
    public ResponseEntity<List<Person>> listInfo(){
        List<Person> people = personRepository.findAll();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    //GetByID Request
    @GetMapping(value = "/communityEmail/{id}")
    public ResponseEntity<Person> listSingleInfo(@PathVariable Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found"));
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    //GetByCity Request
    @GetMapping(value = "/communityEmail")
    public ResponseEntity<CommunityEmailDTO> listCityAddress(
            @RequestParam String city, HttpServletRequest req, CommunityEmailDTO communityEmailDTO){
        log.info("{} : {} : {}", req.getRemoteAddr(), LocalDateTime.now(), communityEmailDTO.toString());
        List<Person> person = personRepository.findPeopleByCity(city);
        if(person.isEmpty()) {
            log.warn("No emails found for city: " + city);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<String> email = person.stream().map(Person::getEmail).toList();
        CommunityEmailDTO emailDTO = new CommunityEmailDTO();
        emailDTO.setEmail(email);
        log.debug("Response: " + email.size());
        return new ResponseEntity<>(emailDTO, HttpStatus.OK);
    }
}
