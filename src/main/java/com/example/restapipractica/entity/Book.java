package com.example.restapipractica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter@Setter
@NoArgsConstructor
public class Book {
    @Id
    @SequenceGenerator(name = "book_gen",sequenceName = "book_seq",allocationSize = 1)
    @GeneratedValue(generator = "book_gen",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    private int bookYear;
    private String publisher;
    @CreatedDate
    private LocalDate publicationDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Author author;
}
