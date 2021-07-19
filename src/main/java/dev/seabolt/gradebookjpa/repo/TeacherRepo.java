package dev.seabolt.gradebookjpa.repo;

import dev.seabolt.gradebookjpa.entity.TeacherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends CrudRepository<TeacherEntity,Long> {
}
