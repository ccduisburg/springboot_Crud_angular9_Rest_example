package com.tutorial.crudexample.controller;

import com.tutorial.crudexample.exceptions.ResourceNotFoundException;
import com.tutorial.crudexample.model.Person;
import com.tutorial.crudexample.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/v1")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/personal")
    public List<Person> getAllPersonal(){
        return personRepository.findAll();
    }

    @GetMapping("/personal/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personalId) throws ResourceNotFoundException {
        Person person=personRepository.findById(personalId).orElseThrow(()->new ResourceNotFoundException("Person not found dor this id: "+personalId));
        return ResponseEntity.ok(person);

    }
    @PostMapping("/personal")
    public Person createPerson(@Valid @RequestBody Person person){
        return personRepository.save(person);
    }

    @PutMapping("/personal/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value="id") Long personId, @Valid @RequestBody Person personDetails ) throws ResourceNotFoundException{
        Person findperson=personRepository.findById(personId).orElseThrow(()->new ResourceNotFoundException("Person not found for this id: "+personId));

        findperson.setFirstName(personDetails.getFirstName());
        findperson.setLastName(personDetails.getLastName());
        findperson.setEmailId(personDetails.getEmailId());

        final Person updatePerson=personRepository.save(findperson);

        return ResponseEntity.ok(updatePerson);

    }
    @DeleteMapping("/personal/{id}")
    public Map<String,Boolean> deletePerson(@PathVariable(value = "id") Long personId) throws ResourceNotFoundException{
        Person deleteperson=personRepository.findById(personId).orElseThrow(()->new ResourceNotFoundException("Person not found for this id::"+personId));
        personRepository.delete(deleteperson);
        Map<String, Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }


}
