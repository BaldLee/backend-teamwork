package com.se418.bookservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class BookServiceApplication {
    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }
 
    private List<Book> bookList = Arrays.asList(
        new Book(1L, "Baeldung goes to the market", "Tim Schimandle"),
        new Book(2L, "Baeldung goes to the park", "Slavisa")
    );

    @GetMapping("/")
    public String getinstanceID(){
        return "Hi there! instance id: " + instanceId;
    }
 
    @GetMapping("/books")
    public List<Book> findAllBooks() {
        return bookList;
    }
 
    @GetMapping("/books/{bookId}")
    public Book findBook(@PathVariable Long bookId) {
        return bookList.stream().filter(b -> b.getId().equals(bookId)).findFirst().orElse(null);
    }
}
