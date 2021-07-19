package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.SubmissionEntity;
import dev.seabolt.springBootGradebook.repo.SubmissionRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Submission")
class SubmissionController {

    private final SubmissionRepo repository;

    SubmissionController(SubmissionRepo repository) {
        this.repository = repository;
    }



    @GetMapping
    List<SubmissionEntity> list() {
        List<SubmissionEntity> list = new ArrayList<>();
        for(SubmissionEntity e : repository.findAll()){
            list.add(e);
        }
        return list;
    }

    @PostMapping
    SubmissionEntity newSubmission(@RequestBody SubmissionEntity newSubmission) {
        return repository.save(newSubmission);
    }


    @GetMapping("/GetByID/{id}")
    SubmissionEntity getByID(@PathVariable Long id) {

        // TODO: 7/19/2021 Add custom exceptions 
        // TODO: 7/19/2021 Add better error handling 
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }

    @GetMapping("/GetByStudentID/{id}")
    List<SubmissionEntity> getByStudentID(@PathVariable Long id) {

        return  repository.getAllByStudentID(id);
    }


    @PutMapping("/UpdateByID/{id}")
    SubmissionEntity replaceSubmission(@RequestBody SubmissionEntity newSubmission, @PathVariable Long id) {

        return repository.findById(id)
                .map(Submission -> {
                    Submission.setStudent(newSubmission.getStudent());
                    Submission.setAssignmentByAssignmentId(newSubmission.getAssignmentByAssignmentId());
                    Submission.setPointsAwarded(newSubmission.getPointsAwarded());
                    Submission.setDateSubmitted(newSubmission.getDateSubmitted());
                    Submission.setComment(newSubmission.getComment());
                    return repository.save(Submission);
                })
                .orElseThrow( () ->
                        new RuntimeException("ID Not found for update")
                );
    }

    @DeleteMapping("/DeleteByID/{id}")
    void deleteSubmission(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
