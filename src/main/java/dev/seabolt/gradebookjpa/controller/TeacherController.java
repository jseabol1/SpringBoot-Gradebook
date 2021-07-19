package dev.seabolt.gradebookjpa.controller;

import dev.seabolt.gradebookjpa.entity.TeacherEntity;
import dev.seabolt.gradebookjpa.repo.TeacherRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Teacher")
class TeacherController {

    private final TeacherRepo repository;

    TeacherController(TeacherRepo repository) {
        this.repository = repository;
    }



    @GetMapping
    List<TeacherEntity> list() {
        List<TeacherEntity> list = new ArrayList<>();
        for(TeacherEntity e : repository.findAll()){
            list.add(e);
        }
        return list;
    }

    @PostMapping
    TeacherEntity newPerson(@RequestBody TeacherEntity newPerson) {
        return repository.save(newPerson);
    }


    @GetMapping("/GetByID/{id}")
    TeacherEntity GetByID(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
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
