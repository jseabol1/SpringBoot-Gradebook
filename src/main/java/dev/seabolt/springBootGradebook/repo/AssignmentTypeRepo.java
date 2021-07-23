package dev.seabolt.springBootGradebook.repo;

import dev.seabolt.springBootGradebook.entity.AssignmentTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssignmentTypeRepo extends CrudRepository<AssignmentTypeEntity, Long>, PagingAndSortingRepository<AssignmentTypeEntity, Long> {

}
