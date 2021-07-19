package dev.seabolt.gradebookjpa.controller;

import dev.seabolt.gradebookjpa.entity.StudentEntity;
import dev.seabolt.gradebookjpa.repo.StudentRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Student")
class StudentController {

    private final StudentRepo repository;

    StudentController(StudentRepo repository) {
        this.repository = repository;
    }



    @GetMapping
    List<StudentEntity> list() {
        List<StudentEntity> list = new ArrayList<>();
        for(StudentEntity e : repository.findAll()){
            list.add(e);
        }
        return list;
    }

    @PostMapping
    StudentEntity newPerson(@RequestBody StudentEntity newPerson) {
        return repository.save(newPerson);
    }


    @GetMapping("/GetByID/{id}")
    StudentEntity GetByID(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
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

    @DeleteMapping("/DeleteByID/{id}")
    void deleteAddress(@PathVariable Long id) {

        try {
            repository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
