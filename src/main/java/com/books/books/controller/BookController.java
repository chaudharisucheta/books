package com.books.books.controller;

import com.books.books.model.Book;
import com.books.books.repository.BookRepository;
import com.books.books.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins ="*")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    ApiResponse api=new ApiResponse();


    //Add a new book
    @PostMapping(value = "/addbook")
    public ApiResponse addBook(@RequestBody Book book){

        api.setStatus(HttpStatus.OK.value());
        api.setMessage("Book added");
        api.setResult(bookRepository.save(book));
        return api;
    }

    //Find a book by id
    @GetMapping(value="/getbook/{id}")
    public ApiResponse getBook(@PathVariable Integer id){
        api.setStatus(HttpStatus.OK.value());
        api.setMessage("Success");
        api.setResult(bookRepository.findById(id));
        return api;
    }


    //BookList
    @GetMapping(value="/getallbooks")
    public ApiResponse getAllBooks(){
        api.setStatus(HttpStatus.OK.value());
        api.setMessage("Success");
        api.setResult(bookRepository.findAll());
        return api;
    }

    //Delete a book by id
    @GetMapping(value="/deletebook/{id}")
    public ApiResponse deleteBook(@PathVariable Integer id){
        api.setStatus(HttpStatus.OK.value());
        api.setMessage("Book deleted");
        //api.setResult(bookRepository.deleteById(id));
        return api;
    }

    //Update a book by id
    @PutMapping(value = "/updatebook")
    public ApiResponse updateBook(@RequestBody Book book){
        Optional<Book> d = bookRepository.findById(book.getBookNo());
        if(d.isPresent()){
            d.get().setBookName(book.getBookName());
            d.get().setAuthor(book.getAuthor());
            d.get().setPublisher(book.getPublisher());
            d.get().setNoOfPages(book.getNoOfPages());
            d.get().setPrice(book.getPrice());
            d.get().setStatus(book.getStatus());
            d.get().setRating(book.getRating());
        }
        bookRepository.save(d.get());
        api.setStatus(HttpStatus.OK.value());
        api.setMessage("Book updated");
        api.setResult("Updated");
        return api;
    }


}
