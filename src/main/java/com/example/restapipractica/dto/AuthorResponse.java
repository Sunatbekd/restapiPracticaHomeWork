package com.example.restapipractica.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter@Setter
public class AuthorResponse {
    private Long id;
    private String fullName;
    private String firstName;
    private String lastName;
    private String nationality;
    private String gender;
    private LocalDate dateOfBirth;
}
