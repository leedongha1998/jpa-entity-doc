package com.example.jpadoc.repository;

import com.example.jpadoc.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRatingGreaterThanEqual(Integer rating);

    List<Review> findByAuthor(String author);

    @Query("SELECT AVG(r.rating) FROM Review r")
    Double findAverageRating();
}
