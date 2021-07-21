package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.TeacherEntity;
import dev.seabolt.springBootGradebook.repo.TeacherRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Teacher")
class TeacherController extends ControllerBase<TeacherEntity,TeacherRepo> {


    TeacherController(TeacherRepo repository) {
        super(repository);
    }


    @PutMapping("/UpdateByID/{id}")
    TeacherEntity replacePerson(@RequestBody TeacherEntity newPerson, @PathVariable Long id) {

        return repository.findById(id)
                .map(Teacher -> {
                    Teacher.setRank(newPerson.getRank());
                    Teacher.setDepartment(newPerson.getDepartment());
                    return repository.save(Teacher);
                })
                .orElseThrow( () ->
                        new RuntimeException("ID Not found for update")
                );
    }

}
