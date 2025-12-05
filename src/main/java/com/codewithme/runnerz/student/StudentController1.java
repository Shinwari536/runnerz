package com.codewithme.runnerz.student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController1 {

    private final StudentRepository studentRepository;
    public StudentController1(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("")
    public Student create(@RequestBody  Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("")
    public Student update(@RequestBody  Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/all")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("")
    public Student findById(@RequestParam int id) {
        return studentRepository.findById(id).get();
    }
}