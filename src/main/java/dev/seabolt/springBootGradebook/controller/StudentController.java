package dev.seabolt.springBootGradebook.controller;

import dev.seabolt.springBootGradebook.entity.StudentEntity;
import dev.seabolt.springBootGradebook.repo.StudentRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Student")
class StudentController extends ControllerBase<StudentEntity, StudentRepo> {


    StudentController(StudentRepo repository) {
        super(repository);
    }


    @PutMapping("/UpdateByID/{id}")
    StudentEntity replacePerson(@RequestBody StudentEntity newPerson, @PathVariable Long id) {

        return repository.findById(id)
                .map(Student -> {
                    Student.setMajor(newPerson.getMajor());
                    return repository.save(Student);
                })
                .orElseThrow( () ->
                        new RuntimeException("ID Not found for update")
                );
    }

}
