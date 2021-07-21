package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.AssignmentEntity;
import dev.seabolt.springBootGradebook.repo.AssignmentRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Assignment")
class AssignmentController extends ControllerBase<AssignmentEntity, AssignmentRepo> {

    AssignmentController(AssignmentRepo repository) {
        super(repository);
    }

    @PutMapping("/UpdateByID/{id}")
    AssignmentEntity updateByID(@RequestBody AssignmentEntity newAssignment, @PathVariable Long id) {

        return repository.findById(id)
                .map(Assignment -> {
                    Assignment.setCourse(newAssignment.getCourse());
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

    @DeleteMapping("DeleteAllByID/{id}")
    void deleteAllByID(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
