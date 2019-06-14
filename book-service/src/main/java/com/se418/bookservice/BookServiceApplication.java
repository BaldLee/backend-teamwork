package com.se418.bookservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class BookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Autowired
    BookRepository bookRepository;
    @GetMapping("/books")
    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }
 
    @GetMapping("/books/{bookId}")
    public Book findBook(@PathVariable Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable long bookId) {
        bookRepository.deleteById(bookId);
        return "delete succeed!";
    }

    @PostMapping("/books")
    private Book insertBook(@RequestBody Book book ) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    private Book updateBook(@PathVariable Long id, @RequestBody Book book){
        if(bookRepository.findById(id).orElseThrow(null) == null)
            return null;
        book.setId(id);
        return bookRepository.save(book);
    }

}
