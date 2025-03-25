package com.example.demo.service;

import jakarta.transaction.Transactional;
import com.example.demo.dto.Message;
import com.example.demo.dto.Person;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonService
{
    @Autowired
    PersonRepository personRepository;

    @Autowired
    MessageRepository messageRepository;

    public ResponseEntity<Person> addMessageToPerson(int personId, Message message)
    {
        if(personRepository.existsById(personId))
        {
            Person person = personRepository.findById(personId).get();
            person.addMessage(message);
            message.setPerson(person);
            message.setTime(LocalDateTime.now());
            return new ResponseEntity<>(personRepository.save(person), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<Person> deleteMessageToPerson(int personId)
    {
        if (personRepository.existsById(personId))
        {
            Person person = personRepository.findById(personId).get();
            messageRepository.deleteAll(person.getMessages());

            person.deleteMessages();
            return new ResponseEntity<>(personRepository.save(person), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Message>> getPersonMessagesById(int id)
    {
        if (personRepository.existsById(id))
        {
            Person person = personRepository.findById(id).get();
            return new ResponseEntity<>(person.getMessages(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> getPersonMessageById(int personId, int messageId)
    {
        if (!personRepository.existsById(personId))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Person person = personRepository.findById(personId).get();

        if (!person.ExistMessage(messageId))
        {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return  new ResponseEntity<>(person.getMessageById(messageId) ,HttpStatus.OK);
    }
}
