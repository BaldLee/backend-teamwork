package com.se418.ratingbookservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value = "book-service", fallback = ClientFallBack.class)
public interface FeignClient {
    @GetMapping("/")
    public String getinstanceID();
    @GetMapping("/books")
    public List<Book> findAllBooks();

    @GetMapping("/books/{bookId}")
    public Book findBook(@PathVariable Long bookId);
}