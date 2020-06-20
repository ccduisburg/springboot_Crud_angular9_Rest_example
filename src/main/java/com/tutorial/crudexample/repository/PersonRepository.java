package com.tutorial.crudexample.repository;

import com.tutorial.crudexample.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
