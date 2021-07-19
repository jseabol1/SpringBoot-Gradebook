package dev.seabolt.gradebookjpa.repo;

import dev.seabolt.gradebookjpa.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepo extends CrudRepository<PersonEntity, Long> {

}
