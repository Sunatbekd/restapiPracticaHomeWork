package com.example.restapipractica.controller;

import com.example.restapipractica.dto.BookRequest;
import com.example.restapipractica.dto.BookResponse;
import com.example.restapipractica.entity.Book;
import com.example.restapipractica.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookApi {

    private final BookService bookService;
    @PostMapping()
    public BookResponse create(@RequestBody BookRequest bookRequest){
        return bookService.addBook(bookRequest);
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id){
        return bookService.findBookById(id);
    }

    @DeleteMapping("{id}")
    public BookResponse deleteBookById(@PathVariable Long id){
        return bookService.deleteBookById(id);
    }

    @GetMapping()
    public List<Book> getAllBooks(){
        return bookService.books();
    }

    @PutMapping("{id}")
    public BookResponse updateBookById(@PathVariable Long id,@RequestBody BookRequest request){
        return bookService.updateBookById(id, request);
    }
}
