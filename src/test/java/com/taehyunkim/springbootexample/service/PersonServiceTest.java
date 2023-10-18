package com.taehyunkim.springbootexample.service;

import com.taehyunkim.springbootexample.exception.PersonNotFoundException;
import com.taehyunkim.springbootexample.model.Person;
import com.taehyunkim.springbootexample.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ActiveProfiles("test")
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    public PersonServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setup() {
        Person person = new Person();
        person.setName("John");
        person.setEmail("john@example.com");
        personRepository.save(person);
    }

    @Test
    public void testAddPerson() {
        Person person = new Person();
        person.setName("John2");
        person.setEmail("john2@example.com");

        when(personRepository.save(person)).thenReturn(person);

        Person result = personService.addNewPerson(person);

        assertEquals("John2", result.getName());
        assertEquals("john2@example.com", result.getEmail());
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