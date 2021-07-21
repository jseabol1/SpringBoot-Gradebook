package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.AssignmentTypeEntity;
import dev.seabolt.springBootGradebook.repo.AssignmentTypeRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/AssignmentType")
class AssignmentTypeController extends ControllerBase<AssignmentTypeEntity, AssignmentTypeRepo> {


    AssignmentTypeController(AssignmentTypeRepo repository) {
        super(repository);
    }


    @PutMapping("/UpdateByID/{id}")
    AssignmentTypeEntity updateByID(@RequestBody AssignmentTypeEntity newAssignmentType, @PathVariable Long id) {

        return repository.findById(id)
                .map(AssignmentType -> {
                    AssignmentType.setName(newAssignmentType.getName());
                    AssignmentType.setWeight(newAssignmentType.getWeight());
                    return repository.save(AssignmentType);
                })
                .orElseThrow( () ->
                        new RuntimeException("ID Not found for update")
                );
    }

}
