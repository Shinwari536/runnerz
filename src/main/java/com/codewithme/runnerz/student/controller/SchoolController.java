package com.codewithme.runnerz.student.controller;

import com.codewithme.runnerz.student.SchoolRepository;
import com.codewithme.runnerz.student.model.School;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolRepository repository;

    public SchoolController(SchoolRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public School save(@RequestBody School school) {
        return repository.save(school);
    }

    @PutMapping("")
    public School update(@RequestBody School school) {
        return repository.save(school);
    }

    @GetMapping("")
    public School findById(@RequestParam Integer id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/all")
    public List<School> findAll() {
        return repository.findAll();
    }

    @GetMapping("/search/{name}")
    public List<School> findByName(@PathVariable String name) {
        return repository.findAllByNameIgnoreCase(name);
    }

    @DeleteMapping("")
    public void deleteById(@RequestParam Integer id) {
        repository.deleteById(id);
    }
}
