package com.example.restapipractica.service;

import com.example.restapipractica.dto.AuthorRequest;
import com.example.restapipractica.dto.AuthorResponse;
import com.example.restapipractica.entity.Author;
import com.example.restapipractica.exeptions.NotFoundException;
import com.example.restapipractica.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;


    public AuthorResponse addAuthor(AuthorRequest request){
    Author author = createAuthor(request);
    return response(authorRepository.save(author));
    }

    public  AuthorResponse findAuthorById(Long id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with id = %s not found",id)));
        return response(author);
    }

    public AuthorResponse deleteAuthorById(Long id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with id = %s not found",id)));
         authorRepository.delete(author);
        return response(author);
    }

    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }

    public Author createAuthor(AuthorRequest request){
        Author author = new Author();
        author.setFullName(request.getFirstName()+" "+request.getLastName());
        author.setGender(request.getGender());
        author.setNationality(request.getNationality());
        author.setDateOfBirth(request.getDateOfBirth());
        return author;
    }

    public AuthorResponse response(Author author){
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setFullName(author.getFullName());
        String[] arr = author.getFullName().split(" ",2);
        authorResponse.setFirstName(arr[0]);
        authorResponse.setLastName(arr[1]);
        authorResponse.setDateOfBirth(author.getDateOfBirth());
        authorResponse.setNationality(author.getNationality());
        authorResponse.setGender(author.getGender());
        return authorResponse;
    }

    public AuthorResponse updateAuthorById(Long id,AuthorRequest request){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with id = %s not found",id)));
        Author author1 = update(author,request);
        return response(author1);
    }

    public Author update(Author author, AuthorRequest request){
        author.setFullName(request.getFirstName()+" "+request.getLastName());
        author.setNationality(request.getNationality());
        author.setGender(request.getGender());
        author.setDateOfBirth(request.getDateOfBirth());
        return authorRepository.save(author);
    }
}
