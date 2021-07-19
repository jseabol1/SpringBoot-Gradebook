package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.PersonEntity;
import dev.seabolt.springBootGradebook.repo.PersonRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/Person")
class PersonController {

    private final PersonRepo repository;

    PersonController(PersonRepo repository) {
        this.repository = repository;
    }



    @GetMapping
    List<PersonEntity> list() {
        List<PersonEntity> list = new ArrayList<>();
        for(PersonEntity e : repository.findAll()){
            list.add(e);
        }
        return list;
    }

    @PostMapping
    PersonEntity newAddress(@RequestBody PersonEntity newPerson) {
        return repository.save(newPerson);
    }


    @GetMapping("/GetByID/{id}")
    PersonEntity GetByID(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }


    @PutMapping("/UpdateByID/{id}")
    PersonEntity replacePerson(@RequestBody PersonEntity newPerson, @PathVariable Long id) {

        return repository.findById(id)
                .map(Person -> {
                    Person.setFirstName(newPerson.getFirstName());
                    Person.setLastName(newPerson.getLastName());
                    Person.setAddressByAddressId(newPerson.getAddressByAddressId());
                    return repository.save(Person);
                })
                .orElseThrow( () ->
                        new RuntimeException("ID Not found for update")
                );
    }

    @DeleteMapping("/DeleteByID/{id}")
    void deleteAddress(@PathVariable Long id) {

        try {
            repository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
