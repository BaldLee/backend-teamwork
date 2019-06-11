package com.se418.ratingbookservice;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class ClientFallBack implements FeignClient {

    @Override
    public String getinstanceID(){
        return "Fallback!";
    }
    @Override
    public List<Book> findAllBooks(){
        return new List<Book>();
    }

    @Override
    public Book findBook(@PathVariable Long bookId){
        return new Book();
    }
}