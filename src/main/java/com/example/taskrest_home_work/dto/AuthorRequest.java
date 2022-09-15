package com.example.taskrest_home_work.dto;

import com.example.taskrest_home_work.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorRequest {
    private String firstName;
    private String lastName;
    private String nationality;
    private Gender gender;
    private LocalDate dateOfBirth;
}
