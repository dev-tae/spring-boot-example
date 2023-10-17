package com.example.controller;

import com.example.model.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    // mapping methods here
    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    public Optional<Person> getPersonById(@PathVariable UUID id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/persons")
    public Person addNewPerson(@RequestBody Person person) {
        return personService.addNewPerson(person);
    }

    // ... add PUT and DELETE endpoints

}
