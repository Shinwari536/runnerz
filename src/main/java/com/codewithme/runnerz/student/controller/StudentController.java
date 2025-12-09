package com.codewithme.runnerz.student.controller;

import com.codewithme.runnerz.student.SchoolRepository;
import com.codewithme.runnerz.student.StudentRepository;
import com.codewithme.runnerz.student.dto.StudentDto;
import com.codewithme.runnerz.student.dto.StudentResponseDto;
import com.codewithme.runnerz.student.model.Student;
import com.codewithme.runnerz.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("")
    public StudentResponseDto create(@RequestBody StudentDto student) {
        return studentService.createNewStudent(student) ;
    }

    @PutMapping("")
    public StudentResponseDto update(@RequestBody  StudentDto dto, @RequestParam Integer id) {
        return studentService.updateStudent(dto, id);
    }

    @GetMapping("/all")
    public List<StudentResponseDto> findAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("")
    public StudentResponseDto findById(@RequestParam int id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/search")
    public List<StudentResponseDto> searchStudent(@RequestParam String query) {
        if (query.length() < 3){
            throw new IllegalArgumentException("query length should be at least 3");
        }
        return studentService.searchStudents(query);
    }
}