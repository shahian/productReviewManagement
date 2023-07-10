package com.shahian.productreviewmanagement.controller;

import com.shahian.productreviewmanagement.model.entity.Review;
import com.shahian.productreviewmanagement.service.interfaces.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/v1/reviews")
    public ResponseEntity<List<Review>> getLatestProductReviews(@RequestParam Long productId) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/v1/review")
    public ResponseEntity<?> createReview(@RequestParam Long productId, @RequestBody Review review) {
        Review review1 = reviewService.addReview(productId, review);
        return new ResponseEntity<>(review1, HttpStatus.CREATED);
    }
    @GetMapping("/v1/unapprovedReviews")
    public ResponseEntity<List<Review>>getUnapprovedReviews(){
       List<Review>reviews= reviewService.getUnapprovedReviews();
       return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

    @PutMapping("/v1/approveReview")
    public ResponseEntity<?> approveReview(@RequestParam  Long reviewId) {

            Review review = reviewService.approveReviewById(reviewId);

            return new ResponseEntity<>("Review has been approved", HttpStatus.OK);

    }
    @PutMapping("/v1/rejectReview")
    public ResponseEntity<?> rejectReview(@RequestParam  Long reviewId) {

        Review review = reviewService.rejectReviewById(reviewId);

        return new ResponseEntity<>("Review has been rejected", HttpStatus.OK);

    }
}



