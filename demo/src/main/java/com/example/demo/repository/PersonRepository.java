package com.example.demo.repository;

import com.example.demo.dto.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer>
{
}