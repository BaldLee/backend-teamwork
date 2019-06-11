package com.se418.bookservice;

import com.se418.bookservice.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class BookServiceApplication {
//    @Value("${eureka.instance.instance-id}")
//    private String instanceId;

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }
 
//    private List<Book> bookList = Arrays.asList(
//        new Book(1L, "Baeldung goes to the market", "Tim Schimandle"),
//        new Book(2L, "Baeldung goes to the park", "Slavisa")
//    );

//    @GetMapping("/")
//    public String getinstanceID(){
//        return "Hi there! instance id: " + instanceId;
//    }
    @Autowired
    BookMapper bookMapper;
    @GetMapping("/books")
    public List<Book> findAllBooks() {
        return bookMapper.selectAll();
    }
 
    @GetMapping("/books/{bookId}")
    public Book findBook(@PathVariable Long bookId) {
        return bookMapper.selectById(bookId).orElse(null);
    }

    @PostMapping("/remove/{bookId}")
    public void deleteBook(@PathVariable long BookId) {
        bookMapper.deleteById(BookId);
    }
    @PostMapping("/insert")
    private void insertBook(
            @RequestBody Book book
    ) {
        bookMapper.insertBook(book);
    }
    @PostMapping("/update")
    private void updateBook(
            @RequestBody Book book
    ){
        bookMapper.updateBook(book);
    }

}
