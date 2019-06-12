package com.se418.ratingbookservice.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void Book(){
        Book book = new Book();
    }

    @Test
    public void getId() {
        Book book = new Book();
        book.setId(1);
        assertEquals(book.getId(),1);
    }

    @Test
    public void setId() {
        Book book = new Book();
        book.setId(1);
        assertEquals(book.getId(),1);
    }

    @Test
    public void getAuthor() {
        Book book = new Book();
        book.setAuthor("a");
        assertEquals(book.getAuthor(),"a");
    }

    @Test
    public void setAuthor() {
        Book book = new Book();
        book.setAuthor("a");
        assertEquals(book.getAuthor(),"a");
    }

    @Test
    public void getTitle() {
        Book book = new Book();
        book.setTitle("a");
        assertEquals(book.getTitle(),"a");
    }

    @Test
    public void setTitle() {
        Book book = new Book();
        book.setTitle("a");
        assertEquals(book.getTitle(),"a");
    }
}