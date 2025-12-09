package com.codewithme.runnerz.student;

import com.codewithme.runnerz.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> searchAllByEmailContainingOrFirstnameContainingOrLastnameIgnoreCase(String email, String firstname, String lastname);
    List<Student> findAllByFirstname(String firstname);
    Student findByEmail(String email);
    List<Student> findAllByAgeGreaterThan(int age);

}
