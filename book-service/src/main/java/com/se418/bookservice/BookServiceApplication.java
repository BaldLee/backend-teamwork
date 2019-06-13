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

    @PostMapping("/remove/{bookId}")
    public void deleteBook(@PathVariable long BookId) {
        bookRepository.deleteById(BookId);
    }

    @PostMapping("/insert")
    private void insertBook(@RequestBody Book book ) {
        bookRepository.save(book);
    }
    @PostMapping("/update/{id}")
    private void updateBook(@PathVariable Long id, @RequestBody Book book){
        book.setId(id);
        bookRepository.save(book);
    }

}
