package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.CourseEntity;
import dev.seabolt.springBootGradebook.repo.CourseRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Course")
class CourseController extends ControllerBase<CourseEntity, CourseRepo> {

    CourseController(CourseRepo repository) {
        super(repository);
    }


    @PutMapping("/UpdateByID/{id}")
    CourseEntity replaceCourse(@RequestBody CourseEntity newCourse, @PathVariable Long id) {

        return repository.findById(id)
                .map(Course -> {
                    Course.setTeacher(newCourse.getTeacher());
                    Course.setName(newCourse.getName());
                    Course.setDateEnd(newCourse.getDateStart());
                    Course.setDateEnd(newCourse.getDateEnd());
                    return repository.save(Course);
                })
                .orElseThrow(() ->
                        new RuntimeException("ID Not found for update")
                );
    }

}
