package com.shahian.productreviewmanagement.service.impl;

import com.shahian.productreviewmanagement.model.entity.Product;
import com.shahian.productreviewmanagement.model.entity.Review;
import com.shahian.productreviewmanagement.repository.ProductRepository;
import com.shahian.productreviewmanagement.repository.ReviewRepository;
import com.shahian.productreviewmanagement.service.interfaces.ReviewService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Review saveReview(Review review) {
        return null;
    }

    @Override
    public List<Review> getReviews() {
        return null;
    }

    @Override
    public Review getReviewById(Long id) {
        return null;
    }

    // calculate the average rating for a product
    @Override
    public Double calculateAverageRating(Long productId) {
        List<Review> reviews = reviewRepository.findAllByProductIdAndIsDeletedFalse(productId);
        OptionalDouble average = reviews.stream()
                .mapToInt(Review::getRating)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0.0;
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
        reviews.stream()
                .sorted(Comparator.comparing(Review::getCreateDateTime).reversed())
                .limit(3)
                .collect(Collectors.toList());

        return reviews;
    }

    @Override
    public Review addReview(Long productId, Review review) {
        Product product = productRepository.findByIdAndIsDeletedFalse(productId).orElseThrow(() -> new NullPointerException("not found"));
        review.setProduct(product);
        return reviewRepository.save(review);


    }

    @Override
    public List<Review> getUnapprovedReviews() {
        List<Review> UnapprovedReviews = reviewRepository.findAllByApprovedFalseAndIsDeletedFalse();
        if(UnapprovedReviews.isEmpty()){
            throw new NullPointerException("list is empty");
        }
        return UnapprovedReviews;

    }

}
