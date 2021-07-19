package dev.seabolt.springBootGradebook.repo;

import dev.seabolt.springBootGradebook.entity.RegistrationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationRepo extends CrudRepository<RegistrationEntity, Long> {

}
