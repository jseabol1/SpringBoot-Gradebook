package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.RegistrationEntity;
import dev.seabolt.springBootGradebook.repo.RegistrationRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Registration")
class RegistrationController extends ControllerBase<RegistrationEntity,RegistrationRepo> {

    RegistrationController(RegistrationRepo repository) {
        super(repository);
    }


    @PutMapping("/UpdateByID/{id}")
    RegistrationEntity replaceRegistration(@RequestBody RegistrationEntity newRegistration, @PathVariable Long id) {

        return repository.findById(id)
                .map(Registration -> {
                    Registration.setCourse(newRegistration.getCourse());
                    Registration.setStudent(newRegistration.getStudent());
                    return repository.save(Registration);
                })
                .orElseThrow( () ->
                        new RuntimeException("ID Not found for update")
                );
    }

}
