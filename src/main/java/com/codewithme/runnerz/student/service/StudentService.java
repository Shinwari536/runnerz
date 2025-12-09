package com.codewithme.runnerz.student.service;

import com.codewithme.runnerz.student.SchoolRepository;
import com.codewithme.runnerz.student.StudentRepository;
import com.codewithme.runnerz.student.dto.StudentDto;
import com.codewithme.runnerz.student.dto.StudentResponseDto;
import com.codewithme.runnerz.student.model.School;
import com.codewithme.runnerz.student.model.Student;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolRepository schoolRepository;


    public StudentResponseDto createNewStudent(StudentDto studentDto) {
        var student = new Student();
        BeanUtils.copyProperties(studentDto, student);

        School school = schoolRepository.findById(studentDto.schoolId()).orElse(null);
        student.setSchool(school);

        Student std = studentRepository.save(student);
        return this.toStudentResponseDto(std);
    }

    public StudentResponseDto updateStudent(StudentDto dto, Integer id) {
        var student = studentRepository.findById(id).orElse(null);
        if(student == null) {
            throw new EntityNotFoundException("Student not found");
        }
        BeanUtils.copyProperties(dto, student);
        School school = schoolRepository.findById(dto.schoolId()).orElse(null);
        student.setSchool(school);

        Student std = studentRepository.save(student);
        return this.toStudentResponseDto(std);
    }

    public StudentResponseDto getStudentById(Integer id) {
        return studentRepository.findById(id)
                .map(this::toStudentResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Student with id: " + id + " not found"));
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map((this::toStudentResponseDto))
                .toList();
    }

    public List<StudentResponseDto> searchStudents(String query){
        return studentRepository.searchAllByEmailContainingOrFirstnameContainingOrLastnameIgnoreCase(query, query, query)
                .stream()
                .map(this::toStudentResponseDto)
                .toList();
    }

    private StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(student.getId(), student.getFirstname(), student.getLastname(), student.getEmail(), student.getAge());
    }
}
