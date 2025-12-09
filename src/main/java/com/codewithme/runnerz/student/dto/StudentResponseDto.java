package com.codewithme.runnerz.student.dto;

public record StudentResponseDto(
        Integer id,
        String firstname,
        String lastname,
        String email,
        Integer age
) {
}
