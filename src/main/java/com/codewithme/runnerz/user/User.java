package com.codewithme.runnerz.user;

import jakarta.validation.constraints.Email;
import org.springframework.data.annotation.Id;

public record User(
        @Id
        Integer id,
        String name,
        String username,
        @Email
        String email,
        Address address,
        String phone,
        String website,
        Company company


) {
}
