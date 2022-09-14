package com.example.restapipractica.dto;

import com.example.restapipractica.entity.Genre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class BookRequest {
    private String name;
    private String description;
    private String publisher;
    private LocalDate publicationDate;
    private Genre genre;
    private Long authorId;
}
