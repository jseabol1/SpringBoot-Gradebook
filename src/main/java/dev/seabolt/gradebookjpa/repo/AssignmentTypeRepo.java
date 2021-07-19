package dev.seabolt.gradebookjpa.repo;

import dev.seabolt.gradebookjpa.entity.AssignmentTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssignmentTypeRepo extends CrudRepository<AssignmentTypeEntity, Long> {

}
