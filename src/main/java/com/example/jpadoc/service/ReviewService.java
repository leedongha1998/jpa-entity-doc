package com.example.jpadoc.service;

import com.example.jpadoc.common.exception.BusinessException;
import com.example.jpadoc.common.exception.ErrorCode;
import com.example.jpadoc.entity.Review;
import com.example.jpadoc.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.REVIEW_NOT_FOUND));
    }

    public List<Review> findByMinRating(Integer minRating) {
        if (minRating < 1 || minRating > 5) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        }
        return reviewRepository.findByRatingGreaterThanEqual(minRating);
    }

    public List<Review> findByAuthor(String author) {
        return reviewRepository.findByAuthor(author);
    }

    public Double getAverageRating() {
        Double avg = reviewRepository.findAverageRating();
        return avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0;
    }

    @Transactional
    public Review create(Review review) {
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        }
        return reviewRepository.save(review);
    }

    @Transactional
    public Review update(Long id, Review request) {
        Review review = findById(id);
        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new BusinessException(ErrorCode.INVALID_INPUT);
        }
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        review.setRating(request.getRating());
        return reviewRepository.save(review);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        reviewRepository.deleteById(id);
    }
}
