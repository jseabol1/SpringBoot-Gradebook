package dev.seabolt.gradebookjpa.controller;

import dev.seabolt.gradebookjpa.entity.AssignmentEntity;
import dev.seabolt.gradebookjpa.repo.AssignmentRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
class AssignmentController {

    private final AssignmentRepo repository;

    AssignmentController(AssignmentRepo repository) {
        this.repository = repository;
    }



    @GetMapping
    List<AssignmentEntity> list() {
        List<AssignmentEntity> list = new ArrayList<>();
        for(AssignmentEntity e : repository.findAll()){
            list.add(e);
        }
        return list;
    }

    @PostMapping
    AssignmentEntity newAssignment(@RequestBody AssignmentEntity newAssignment) {
        return repository.save(newAssignment);
    }


    @GetMapping("/GetByID/{id}")
    AssignmentEntity GetByID(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
    }


    @PutMapping("/UpdateByID/{id}")
    AssignmentEntity replaceAssignment(@RequestBody AssignmentEntity newAssignment, @PathVariable Long id) {

        return repository.findById(id)
                .map(Assignment -> {
                    Assignment.setCourseByCourseId(newAssignment.getCourseByCourseId());
                    Assignment.setName(newAssignment.getName());
                    Assignment.setPointsAvailable(newAssignment.getPointsAvailable());
                    Assignment.setDateAssigned(newAssignment.getDateAssigned());
                    Assignment.setDateDue(newAssignment.getDateDue());
                    Assignment.setRepeatable(newAssignment.isRepeatable());
                    Assignment.setHasBonusPoints(newAssignment.isHasBonusPoints());
                    return repository.save(Assignment);
                })
                .orElseThrow( () ->
                        new RuntimeException("ID Not found for update")
                );
    }

    @DeleteMapping("/DeleteByID/{id}")
    void deleteAssignment(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
