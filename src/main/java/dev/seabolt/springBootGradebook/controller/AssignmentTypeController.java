package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.AssignmentTypeEntity;
import dev.seabolt.springBootGradebook.repo.AssignmentTypeRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/AssignmentType")
class AssignmentTypeController {

    private final AssignmentTypeRepo repository;

    AssignmentTypeController(AssignmentTypeRepo repository) {
        this.repository = repository;
    }



    @GetMapping
    List<AssignmentTypeEntity> list() {
        List<AssignmentTypeEntity> list = new ArrayList<>();
        for(AssignmentTypeEntity e : repository.findAll()){
            list.add(e);
        }
        return list;
    }

    @PostMapping
    AssignmentTypeEntity newAssignmentType(@RequestBody AssignmentTypeEntity newAssignmentType) {
        return repository.save(newAssignmentType);
    }


    @GetMapping("/GetByID/{id}")
    AssignmentTypeEntity GetByID(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AssignmentType not found"));
    }


    @PutMapping("/UpdateByID/{id}")
    AssignmentTypeEntity replaceAssignmentType(@RequestBody AssignmentTypeEntity newAssignmentType, @PathVariable Long id) {

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

    @DeleteMapping("/DeleteByID/{id}")
    void deleteAssignmentType(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
