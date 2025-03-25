package com.example.demo.controller;

import com.example.demo.service.PersonService;
import com.example.demo.dto.Message;
import com.example.demo.dto.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController
{
    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public  Iterable<Person> getPersons()
    {
        return repository.findAll();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id)
    {
        return repository.findById(id);
    }

    @GetMapping("/person/{id}/message")
    public ResponseEntity<List<Message>> getMessages(@PathVariable int id)
    {
        return personService.getPersonMessagesById(id);
    }

    @GetMapping("/person/{person_id}/message/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int person_id, @PathVariable int message_id)
    {
        return personService.getPersonMessageById(person_id, message_id);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person)
    {
        if(repository.existsById(id))
        {
            person.setId(id);
            return new ResponseEntity<>(repository.save(person), HttpStatus.OK);
        }

        return new ResponseEntity<>(repository.save(person), HttpStatus.CREATED);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person)
    {
        return repository.save(person);
    }

    @PostMapping("/person/{id}/message")
    public ResponseEntity<Person> addMessage(@PathVariable int id, @RequestBody Message message)
    {
        return personService.addMessageToPerson(id, message);
    }

    @DeleteMapping("/person/{id}/message")
    public ResponseEntity<Person> deleteMessage(@PathVariable int id)
    {
        return personService.deleteMessageToPerson(id);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id)
    {
        repository.deleteById(id);
    }

    @DeleteMapping("/person")
    public void deletePersons()
    {
        repository.deleteAll();

    }
}