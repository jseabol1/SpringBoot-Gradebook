package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.SubmissionEntity;
import dev.seabolt.springBootGradebook.repo.SubmissionRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Submission")
class SubmissionController extends ControllerBase<SubmissionEntity, SubmissionRepo> {

    SubmissionController(SubmissionRepo repository) {
        super(repository);
    }


    @PutMapping("/UpdateByID/{id}")
    SubmissionEntity updateByID(@RequestBody SubmissionEntity newSubmission, @PathVariable Long id) {

        return repository.findById(id)
                .map(Submission -> {
                    Submission.setStudent(newSubmission.getStudent());
                    Submission.setAssignment(newSubmission.getAssignment());
                    Submission.setPointsAwarded(newSubmission.getPointsAwarded());
                    Submission.setDateSubmitted(newSubmission.getDateSubmitted());
                    Submission.setComment(newSubmission.getComment());
                    return repository.save(Submission);
                })
                .orElseThrow(() ->
                        new RuntimeException("ID Not found for update")
                );
    }

    @GetMapping("/SearchByStudentID/{id}")
    List<SubmissionEntity> searchByStudentID(@PathVariable Long id) {
        return repository.getAllByStudentId(id);
    }

    @GetMapping("/GetByCourseAndStudent{courseID}{studentID}")
    List<SubmissionEntity> getByCourseAndStudent(@RequestParam(name = "courseID") long courseID, @RequestParam(name = "studentID") long studentID) {
        return repository.getAllByAssignmentCourseIdAndStudentId(courseID, studentID);
    }

    @DeleteMapping("/DeleteByCourseAndStudent{courseID}{studentID}")
    void deleteByCourseAndStudent(@RequestParam(name = "courseID") long courseID, @RequestParam(name = "studentID") long studentID) {
        List<SubmissionEntity> se = repository.getAllByAssignmentCourseIdAndStudentId(courseID, studentID);
        repository.deleteAll(se);
    }

    @DeleteMapping("/DeleteAllByAssignmentID/{id}")
    void deleteByAssignmentID(@PathVariable Long id) {
        repository.deleteAll(repository.getAllByAssignmentId(id));
    }

}
