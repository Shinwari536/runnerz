package com.codewithme.runnerz.student.model;

import jakarta.persistence.*;

@Entity
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bio;
    @JoinColumn(name = "student_id")
    @OneToOne
    private Student student;


    public StudentProfile(String bio) {
        this.bio = bio;
    }

    public StudentProfile() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
