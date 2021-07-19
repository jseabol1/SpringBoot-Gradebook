package dev.seabolt.gradebookjpa.repo;

import dev.seabolt.gradebookjpa.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends CrudRepository<StudentEntity, Long> {

}
