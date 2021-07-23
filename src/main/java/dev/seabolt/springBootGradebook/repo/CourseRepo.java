package dev.seabolt.springBootGradebook.repo;

import dev.seabolt.springBootGradebook.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepo extends CrudRepository<CourseEntity, Long>, PagingAndSortingRepository<CourseEntity, Long> {

}
