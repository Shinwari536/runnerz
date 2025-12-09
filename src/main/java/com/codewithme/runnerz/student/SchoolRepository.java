package com.codewithme.runnerz.student;

import com.codewithme.runnerz.student.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School,Integer> {

    List<School> findAllByNameIgnoreCase(String name);
}
