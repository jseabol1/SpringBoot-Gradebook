package dev.seabolt.springBootGradebook.repo;

import dev.seabolt.springBootGradebook.entity.SubmissionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubmissionRepo extends CrudRepository<SubmissionEntity, Long> {

    @Query("SELECT s from SubmissionEntity s where s.student.id = :studentID")
    List<SubmissionEntity> getAllByStudentID( @Param("studentID") Long studentID );
}
