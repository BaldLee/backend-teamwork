package com.se418.ratingbookservice;

import com.se418.ratingbookservice.entity.Book;
import com.se418.ratingbookservice.entity.Rate;
import com.se418.ratingbookservice.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Calendar;

@RestController
public class RatingBookController {
    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/rate")
    public String rate(@RequestParam long userId, @RequestParam long bookId, @RequestParam int point) {
        //get current time
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());

        //check legality
        Book book = restTemplate.getForObject("http://book-service/books/" + bookId, Book.class);
        if (book == null) return "rate failed";
//        HttpSession session = null;
//        String userCheck = restTemplate.postForObject("http://book-server/login/check_session", session, String.class);
//        if (userCheck == "failure") return "rate failed";

        Rate rate = new Rate(userId, bookId, point, now);
        rateRepository.save(rate);
        return "rate succeeded";
    }
}
