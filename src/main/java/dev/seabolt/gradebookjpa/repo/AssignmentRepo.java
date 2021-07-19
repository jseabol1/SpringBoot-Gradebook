package dev.seabolt.gradebookjpa.repo;

import dev.seabolt.gradebookjpa.entity.AssignmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssignmentRepo extends CrudRepository<AssignmentEntity, Long> {

}
