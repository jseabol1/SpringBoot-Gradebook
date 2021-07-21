package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.PersonEntity;
import dev.seabolt.springBootGradebook.repo.PersonRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Person")
class PersonController extends ControllerBase<PersonEntity, PersonRepo> {


    PersonController(PersonRepo repository) {
        super(repository);
    }


    @PutMapping("/UpdateByID/{id}")
    PersonEntity replacePerson(@RequestBody PersonEntity newPerson, @PathVariable Long id) {

        return repository.findById(id)
                .map(Person -> {
                    Person.setFirstName(newPerson.getFirstName());
                    Person.setLastName(newPerson.getLastName());
                    Person.setAddress(newPerson.getAddress());
                    return repository.save(Person);
                })
                .orElseThrow( () ->
                        new RuntimeException("ID Not found for update")
                );
    }

}
