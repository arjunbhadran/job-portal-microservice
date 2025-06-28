package com.example.review.reviews;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    Review getReviewById(Long companyId,Long reviewId);
    Boolean createReview(Long companyId,Review review);
    Boolean updateReview(Long reviewId, Review review);
    String deleteReview(Long reviewId);
}
