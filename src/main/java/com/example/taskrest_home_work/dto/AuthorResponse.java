package com.example.taskrest_home_work.dto;

import com.example.taskrest_home_work.enums.Gender;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AuthorResponse {
    private  Long id;
    private String fullName;
    private String firstName;
    private String lastName;
    private String nationality;
    private Gender gender;
    private int age;

    public AuthorResponse(Long id, String fullName, String firstName, String lastName, String nationality, Gender gender, int age) {
        this.id = id;
        this.fullName = fullName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.gender = gender;
        this.age = age;
    }
}
