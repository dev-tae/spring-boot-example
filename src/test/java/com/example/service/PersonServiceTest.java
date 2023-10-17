package com.example.service;

import com.example.exception.PersonNotFoundException;
import com.example.model.Person;
import com.example.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    public PersonServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPerson() {
        Person person = new Person();
        person.setName("John");
        person.setEmail("john@example.com");

        when(personRepository.save(person)).thenReturn(person);

        Person result = personService.addNewPerson(person);

        assertEquals("John", result.getName());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    public void testFindPerson() {
        UUID id = UUID.randomUUID();

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(id));
    }

    @Test
    public void testDeletePerson_Success() {
        UUID id = UUID.randomUUID();

        when(personRepository.findById(id)).thenReturn(Optional.of(new Person()));

        doNothing().when(personRepository).deleteById(id);

        personService.deletePerson(id);

        verify(personRepository, times(1)).deleteById(id);

    }

    @Test
    public void testDeletePerson_NotFound() {
        UUID id = UUID.randomUUID();

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.deletePerson(id));
    }

}