package com.example.restapipractica.controller;

import com.example.restapipractica.dto.AuthorRequest;
import com.example.restapipractica.dto.AuthorResponse;
import com.example.restapipractica.entity.Author;
import com.example.restapipractica.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorApi {
    private final AuthorService authorService;
    @PostMapping
    public AuthorResponse create(@RequestBody AuthorRequest request){
        return authorService.addAuthor(request);
    }

    @GetMapping("{id}")
    public AuthorResponse getById(@PathVariable Long id){
        return authorService.findAuthorById(id);
    }

    @DeleteMapping("{id}")
    public AuthorResponse deleteById(@PathVariable Long id){
        return authorService.deleteAuthorById(id);
    }

    @PutMapping("{id}")
    public AuthorResponse update(@PathVariable Long id,@RequestBody AuthorRequest request){
        return authorService.updateAuthorById(id,request);
    }

    @GetMapping
    public List<Author>authors(){
        return authorService.getAllAuthor();
    }
}
