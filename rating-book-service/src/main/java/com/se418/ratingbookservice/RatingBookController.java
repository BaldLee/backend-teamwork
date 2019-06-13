package com.se418.ratingbookservice;

import com.se418.ratingbookservice.entity.Book;
import com.se418.ratingbookservice.entity.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Calendar;

@RestController
public class RatingBookController {
    @Autowired
    private BookClient bookClient;

    @Autowired
    private RateRepository rateRepository;

    @GetMapping("/insertRate")
    public String rate(@RequestParam long userId, @RequestParam long bookId, @RequestParam int point) {
        //get current time
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTimeInMillis());

        //check legality
        Book book = bookClient.getBook(bookId).orElse(null);

        if (book == null) return "rate failed";
        Rate rate = new Rate(userId, bookId, point, now);
        rateRepository.save(rate);
        return "rate succeeded";
    }

    @GetMapping("/getRate/users/{userId}")
    public Iterable<Rate> getRate(@PathVariable Long userId){
        return rateRepository.findByUserId(userId);
    }

    @GetMapping("/getRate/books/{bookId}")
    public Iterable<Rate> getRateByBookId(@PathVariable Long bookId){
        return rateRepository.findByBookId(bookId);
    }
}
