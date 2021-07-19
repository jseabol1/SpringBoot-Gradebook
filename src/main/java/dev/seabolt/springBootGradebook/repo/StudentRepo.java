package dev.seabolt.springBootGradebook.repo;

import dev.seabolt.springBootGradebook.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends CrudRepository<StudentEntity, Long> {

}
