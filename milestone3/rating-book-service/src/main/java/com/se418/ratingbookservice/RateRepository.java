package com.se418.ratingbookservice;

import com.se418.ratingbookservice.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate,Long> {
    Iterable<Rate> findByUserId(Long userId);
    Iterable<Rate> findByBookId(Long bookId);
}