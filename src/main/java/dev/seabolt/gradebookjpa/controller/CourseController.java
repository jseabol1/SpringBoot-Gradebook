package dev.seabolt.gradebookjpa.controller;

import dev.seabolt.gradebookjpa.entity.CourseEntity;
import dev.seabolt.gradebookjpa.repo.CourseRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Course")
class CourseController {

    private final CourseRepo repository;

    CourseController(CourseRepo repository) {
        this.repository = repository;
    }



    @GetMapping
    List<CourseEntity> list() {
        List<CourseEntity> list = new ArrayList<>();
        for(CourseEntity e : repository.findAll()){
            list.add(e);
        }
        return list;
    }

    @PostMapping
    CourseEntity newCourse(@RequestBody CourseEntity newCourse) {
        return repository.save(newCourse);
    }


    @GetMapping("/GetByID/{id}")
    CourseEntity GetByID(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }


    @PutMapping("/UpdateByID/{id}")
    CourseEntity replaceCourse(@RequestBody CourseEntity newCourse, @PathVariable Long id) {

        return repository.findById(id)
                .map(Course -> {
                    Course.setTeacherByTeacherId(newCourse.getTeacherByTeacherId());
                    Course.setName(newCourse.getName());
                    Course.setDateEnd(newCourse.getDateStart());
                    Course.setDateEnd(newCourse.getDateEnd());
                    return repository.save(Course);
                })
                .orElseThrow( () ->
                        new RuntimeException("ID Not found for update")
                );
    }

    @DeleteMapping("/DeleteByID/{id}")
    void deleteCourse(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
