package dev.seabolt.gradebookjpa.controller;

import dev.seabolt.gradebookjpa.entity.RegistrationEntity;
import dev.seabolt.gradebookjpa.repo.RegistrationRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Registration")
class RegistrationController {

    private final RegistrationRepo repository;

    RegistrationController(RegistrationRepo repository) {
        this.repository = repository;
    }



    @GetMapping
    List<RegistrationEntity> list() {
        List<RegistrationEntity> list = new ArrayList<>();
        for(RegistrationEntity e : repository.findAll()){
            list.add(e);
        }
        return list;
    }

    @PostMapping
    RegistrationEntity newRegistration(@RequestBody RegistrationEntity newRegistration) {
        return repository.save(newRegistration);
    }


    @GetMapping("/GetByID/{id}")
    RegistrationEntity GetByID(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
    }


    @PutMapping("/UpdateByID/{id}")
    RegistrationEntity replaceRegistration(@RequestBody RegistrationEntity newRegistration, @PathVariable Long id) {

        return repository.findById(id)
                .map(Registration -> {
                    Registration.setCourseByCourseId(newRegistration.getCourseByCourseId());
                    Registration.setStudentByStudentId(newRegistration.getStudentByStudentId());
                    return repository.save(Registration);
                })
                .orElseThrow( () ->
                        new RuntimeException("ID Not found for update")
                );
    }

    @DeleteMapping("/DeleteByID/{id}")
    void deleteRegistration(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
