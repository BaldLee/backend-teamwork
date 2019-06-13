package com.se418.ratingbookservice;

import com.se418.ratingbookservice.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Component
@FeignClient(name="book", url="http://localhost:8099")
public interface BookClient {
    @RequestMapping(method = RequestMethod.GET, value="/books")
    Iterable<Book> getBooks();

    @RequestMapping(method = RequestMethod.GET, value="/books/{id}")
    Optional<Book> getBook(@PathVariable Long id);
}
