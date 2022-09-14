package com.example.restapipractica.service;

import com.example.restapipractica.dto.BookRequest;
import com.example.restapipractica.dto.BookResponse;
import com.example.restapipractica.entity.Author;
import com.example.restapipractica.entity.Book;
import com.example.restapipractica.exeptions.NotFoundException;
import com.example.restapipractica.repository.AuthorRepository;
import com.example.restapipractica.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookResponse addBook(BookRequest request){
        Book book = create(request);
        return response(book);
    }

    public BookResponse findBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id = %s not found",id)));
        return response(book);
    }

    public BookResponse deleteBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id = %s not found",id)));
        bookRepository.delete(book);
        return response(book);
    }

    public List<Book>books(){
        return bookRepository.findAll();
    }

    public BookResponse updateBookById(Long id, BookRequest request){
    Book book = bookRepository.findById(id).get();
    Book book1 = update(book,request);
    return response(book1);
    }

    public Book update(Book book, BookRequest request){
        Author author = authorRepository.findById(request.getAuthorId()).get();
        book.setName(request.getName());
        book.setDescription(request.getDescription());
        book.setGenre(request.getGenre());
        book.setPublisher(request.getPublisher());
        book.setPublicationDate(request.getPublicationDate());
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book create(BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId()).get();
        Book book = new Book();
        book.setName(request.getName());
        book.setDescription(request.getDescription());
        book.setGenre(request.getGenre());
        book.setPublisher(request.getPublisher());
        book.setPublicationDate(request.getPublicationDate());
        book.setBookYear(Period.between(request.getPublicationDate(), LocalDate.now()).getYears());
        book.setAuthor(author);
        author.setBooks(List.of(book));
        return bookRepository.save(book);
    }

    public BookResponse response(Book book){
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setName(book.getName());
        bookResponse.setDescription(book.getDescription());
        bookResponse.setGenre(book.getGenre());
        bookResponse.setBookYear(Period.between(book.getPublicationDate(), LocalDate.now()).getYears());
        bookResponse.setPublisher(book.getPublisher());
        bookResponse.setPublicationDate(book.getPublicationDate());
        return bookResponse;
    }
}
