package dev.seabolt.springBootGradebook.repo;

import dev.seabolt.springBootGradebook.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepo extends CrudRepository<PersonEntity, Long>, PagingAndSortingRepository<PersonEntity, Long> {

}
