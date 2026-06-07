package com.example.jpadoc.controller;

import com.example.jpadoc.entity.Review;
import com.example.jpadoc.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<Review> getAll() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public Review getById(@PathVariable Long id) {
        return reviewService.findById(id);
    }

    @GetMapping("/filter")
    public List<Review> getByMinRating(@RequestParam Integer minRating) {
        return reviewService.findByMinRating(minRating);
    }

    @GetMapping("/author/{author}")
    public List<Review> getByAuthor(@PathVariable String author) {
        return reviewService.findByAuthor(author);
    }

    @GetMapping("/average-rating")
    public Double getAverageRating() {
        return reviewService.getAverageRating();
    }

    @PostMapping
    public Review create(@RequestBody Review review) {
        return reviewService.create(review);
    }

    @PutMapping("/{id}")
    public Review update(@PathVariable Long id, @RequestBody Review review) {
        return reviewService.update(id, review);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
