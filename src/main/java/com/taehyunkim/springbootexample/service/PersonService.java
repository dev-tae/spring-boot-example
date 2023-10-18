package com.taehyunkim.springbootexample.service;

import com.taehyunkim.springbootexample.exception.PersonNotFoundException;
import com.taehyunkim.springbootexample.model.Person;
import com.taehyunkim.springbootexample.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    // methods to handle CRUD operations
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(UUID id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person not found with id " + id));
    }

    public Person addNewPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(UUID id, Person personDetails) {
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            person.setName(personDetails.getName());
            person.setEmail(personDetails.getEmail());
            return personRepository.save(person);
        } else {
            throw new PersonNotFoundException("com.example.model.Person not found with id " + id);
        }
    }

    public void deletePerson(UUID id) {
        if (personRepository.findById(id).isPresent()) {
            personRepository.deleteById(id);
        } else {
            throw new PersonNotFoundException("Person not found with id " + id);
        }
    }
}
