package com.codewithme.runnerz.student.dto;

public record StudentDto(
        String firstname,
        String lastname,
        String email,
        Integer age,
        Integer schoolId
) {
}
