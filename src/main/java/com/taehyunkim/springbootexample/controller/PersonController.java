package com.example.controller;

import com.example.model.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable UUID id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    public Person addNewPerson(@RequestBody Person person) {
        return personService.addNewPerson(person);
    }

    @PostMapping("/{id}")
    public Person updatePerson(@PathVariable UUID id, @RequestBody Person personDetails) {
        return personService.updatePerson(id, personDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable UUID id) {
        personService.deletePerson(id);
    }
}
