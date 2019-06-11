package com.se418.imageservice;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image, String> {
    Optional<Image> findByBookId(Long bookId);
    void deleteByBookId(Long bookId);
}
