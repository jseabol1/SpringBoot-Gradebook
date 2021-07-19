package dev.seabolt.springBootGradebook.repo;

import dev.seabolt.springBootGradebook.entity.AssignmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssignmentRepo extends CrudRepository<AssignmentEntity, Long> {

}
