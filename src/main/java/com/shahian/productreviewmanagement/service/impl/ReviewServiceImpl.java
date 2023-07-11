package com.shahian.productreviewmanagement.service.impl;

import com.shahian.productreviewmanagement.exception.GeneralFoundException;
import com.shahian.productreviewmanagement.exception.ProductNotFoundException;
import com.shahian.productreviewmanagement.exception.ReviewNotFoundException;
import com.shahian.productreviewmanagement.exception.UserNotFoundException;
import com.shahian.productreviewmanagement.model.entity.Product;
import com.shahian.productreviewmanagement.model.entity.Review;
import com.shahian.productreviewmanagement.model.entity.User;
import com.shahian.productreviewmanagement.repository.ProductRepository;
import com.shahian.productreviewmanagement.repository.ReviewRepository;
import com.shahian.productreviewmanagement.repository.UserRepository;
import com.shahian.productreviewmanagement.service.interfaces.ReviewService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Review getReviewById(Long id) {
        Optional<Review> review = Optional.ofNullable(reviewRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ReviewNotFoundException("the  Review is not Exist")));
        return review.get();

    }

    private Review approveReview(Review review) {
        review.setApproved(true);
        return reviewRepository.save(review);
    }


    // calculate the average rating for a product
    @Override
    public Double calculateAverageRating(Long productId) {
        try {
            List<Review> reviews = reviewRepository.findAllByProductIdAndIsDeletedFalse(productId);
            OptionalDouble average = reviews.stream()
                    .mapToInt(Review::getRating)
                    .average();
            return average.isPresent() ? average.getAsDouble() : 0.0;
        } catch (Exception ex) {
            throw new GeneralFoundException(ex.getMessage());
        }
    }

    // get the count of reviews for a product
    public Long getReviewCount(Long productId) {
        return reviewRepository.countByProductIdAndIsDeletedFalse(productId);
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {

        List<Review> reviews = reviewRepository.findAllByProductIdAndIsDeletedFalse(productId);
        if (reviews.isEmpty()) {
            return null;
        }
        return reviews.stream()
                .sorted(Comparator.comparing(Review::getCreateDateTime).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public Review addReview(Long productId, Review review) {
        Product product = productRepository.findByIdAndIsDeletedFalse(productId)
                .orElseThrow(() -> new ProductNotFoundException("the product is not exist"));
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new UserNotFoundException("the User is not exist"));
        review.setProduct(product);
        review.setUser(user);
        return reviewRepository.save(review);


    }

    @Override
    public List<Review> getUnapprovedReviews() {
        List<Review> unapprovedReviews = reviewRepository.findAllByApprovedFalseAndIsDeletedFalse();
        if (unapprovedReviews.isEmpty()) {
            return Collections.emptyList();
        }

        return unapprovedReviews;

    }

    @Override
    public Review approveReviewById(Long reviewId) {
        Review reviewById = getReviewById(reviewId);
        return approveReview(reviewById);
    }

    @Override
    public Review rejectReviewById(Long reviewId) {
        Review review = getReviewById(reviewId);
        return rejectReviewReview(review);
    }

    private Review rejectReviewReview(Review review) {
        review.setApproved(false);
        return reviewRepository.save(review);
    }

}
