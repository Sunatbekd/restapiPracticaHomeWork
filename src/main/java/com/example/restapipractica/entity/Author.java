package com.example.restapipractica.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter@Setter
@NoArgsConstructor
public class Author {
    @Id
    @SequenceGenerator(name = "author_gen",sequenceName = "author_seq",allocationSize = 1)
    @GeneratedValue(generator = "author_gen",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fullName;
    private String nationality;
    private String gender;
    private LocalDate dateOfBirth;
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy = "author")
    private List<Book> books;

}
