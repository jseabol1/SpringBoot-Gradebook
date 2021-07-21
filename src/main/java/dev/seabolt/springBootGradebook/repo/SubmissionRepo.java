package dev.seabolt.springBootGradebook.repo;

import dev.seabolt.springBootGradebook.entity.SubmissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubmissionRepo extends CrudRepository<SubmissionEntity, Long> {

    List<SubmissionEntity> getAllByStudentId(Long studentID);

    List<SubmissionEntity> getAllByAssignmentCourseIdAndStudentId(long courseID, long studentID);

    //@Query("DELETE FROM SubmissionEntity se WHERE se.assignment.id = :id")

    List<SubmissionEntity> getAllByAssignmentId(long id);
}
