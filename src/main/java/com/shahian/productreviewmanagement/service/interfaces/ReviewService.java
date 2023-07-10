package com.shahian.productreviewmanagement.service.interfaces;

import com.shahian.productreviewmanagement.model.entity.Review;

import java.util.List;

public interface ReviewService {
    Review saveReview(Review review);
    List<Review> getReviews();
    Review getReviewById(Long id);
    Double calculateAverageRating(Long productId);
    Long getReviewCount(Long productId);

    List<Review> getReviewsByProductId(Long productId);

    Review addReview(Long productId, Review review);

    List<Review> getUnapprovedReviews();

}
