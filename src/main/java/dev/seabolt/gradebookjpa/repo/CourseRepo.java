package dev.seabolt.gradebookjpa.repo;

import dev.seabolt.gradebookjpa.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepo extends CrudRepository<CourseEntity, Long> {

}
