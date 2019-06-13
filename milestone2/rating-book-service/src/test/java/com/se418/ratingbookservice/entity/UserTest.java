package com.se418.ratingbookservice.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getUserId() {
        User user = new User();
        user.setUserId(1);
        assertEquals(user.getUserId(),1);
    }

    @Test
    public void setUserId() {
        User user = new User();
        user.setUserId(1);
        assertEquals(user.getUserId(),1);
    }

    @Test
    public void getUsername() {
        User user = new User();
        user.setUsername("a");
        assertEquals(user.getUsername(),"a");
    }

    @Test
    public void setUsername() {
        User user = new User();
        user.setUsername("a");
        assertEquals(user.getUsername(),"a");
    }

    @Test
    public void getPassword() {
        User user = new User();
        user.setPassword("a");
        assertEquals(user.getPassword(),"a");
    }

    @Test
    public void setPassword() {
        User user = new User();
        user.setPassword("a");
        assertEquals(user.getPassword(),"a");
    }
}