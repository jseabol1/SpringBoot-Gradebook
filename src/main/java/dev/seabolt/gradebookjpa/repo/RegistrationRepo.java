package dev.seabolt.gradebookjpa.repo;

import dev.seabolt.gradebookjpa.entity.RegistrationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationRepo extends CrudRepository<RegistrationEntity, Long> {

}
