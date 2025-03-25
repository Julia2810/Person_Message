package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Person
{
    @Id
    @GeneratedValue
    private int id;
    private String firstname;
    private String surname;
    private String lastname;
    private LocalDate birthday;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Message> messages;

    public Person()
    {
    }

    public Person(String firstname, String surname, String lastname, LocalDate birthday, List<Message> messages)
    {

        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.messages = messages;
    }

    public void addMessage(Message message)
    {
        messages.add(message);
    }

    public void deleteMessage(Message message)
    {
        messages.clear();
    }

    public void deleteMessages()
    {
        messages.clear();
    }

    public boolean ExistMessage(int messageId)
    {
        for (Message m: messages)
        {
            if(m.getId() == messageId)
            {
                return true;
            }
        }

        return false;
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public void setBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
    }

    public List<Message> getMessages()
    {
        return messages;
    }

    public Message getMessageById(int messageId)
    {
        for (Message m: messages)
        {
            if(m.getId() == messageId)
            {
                return m;
            }
        }

        return null;
    }

    public void setMessages(List<Message> messages)
    {
        this.messages = messages;
    }
}