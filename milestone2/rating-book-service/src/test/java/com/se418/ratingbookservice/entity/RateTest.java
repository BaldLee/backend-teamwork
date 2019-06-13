package com.se418.ratingbookservice.entity;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.Assert.*;

public class RateTest {

    @Test
    public void getId() {
        Rate rate = new Rate();
        rate.setId(1);
        assertEquals(rate.getId(), 1);
    }

    @Test
    public void setId() {
        Rate rate = new Rate();
        rate.setId(1);
        assertEquals(rate.getId(), 1);
    }

    @Test
    public void getUserId() {
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());
        Rate rate = new Rate(1, 1, 1, now);
        assertEquals(rate.getUserId(), 1);
    }

    @Test
    public void setUserId() {
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());
        Rate rate = new Rate(1, 1, 1, now);
        rate.setUserId(2);
        assertEquals(rate.getUserId(), 2);
    }

    @Test
    public void getBookId() {
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());
        Rate rate = new Rate(1, 1, 1, now);
        assertEquals(rate.getBookId(), 1);
    }

    @Test
    public void setBookId() {
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());
        Rate rate = new Rate(1, 1, 1, now);
        rate.setBookId(2);
        assertEquals(rate.getBookId(), 2);
    }

    @Test
    public void getRateTime() {
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());
        Rate rate = new Rate(1, 1, 1, now);
        assertEquals(rate.getRateTime(), now);
    }

    @Test
    public void setRateTime() {
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());
        Rate rate = new Rate(1, 1, 1, now);
        now.after(now);
        rate.setRateTime(now);
        assertEquals(rate.getRateTime(), now);
    }

    @Test
    public void getPoint() {
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());
        Rate rate = new Rate(1, 1, 1, now);
        assertEquals(rate.getPoint(), 1);
    }

    @Test
    public void setPoint() {
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());
        Rate rate = new Rate(1, 1, 1, now);
        rate.setPoint(2);
        assertEquals(rate.getPoint(), 2);
    }
}