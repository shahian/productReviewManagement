package com.shahian.productreviewmanagement.controller;

import com.shahian.productreviewmanagement.model.entity.Review;
import com.shahian.productreviewmanagement.service.interfaces.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "three Latest Reviews",description = "Display the last three comments of the desired product according to the last comment registration time")
    @GetMapping("/v1/threeLatestReviews")
    public ResponseEntity<List<Review>> getLatestProductReviews(@RequestParam Long productId) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @Operation(summary = "create Review",description = "Create comments and ratings for the product")
    @PostMapping("/v1/review")
    public ResponseEntity<?> createReview(@RequestParam Long productId, @RequestBody Review review) {
        Review review1 = reviewService.addReview(productId, review);
        return new ResponseEntity<>(review1, HttpStatus.CREATED);
    }

    @Operation(summary = "unapproved Reviews",description = "Get Unconfirmed Comments ")
    @GetMapping("/v1/unapprovedReviews")
    public ResponseEntity<List<Review>>getUnapprovedReviews(){
       List<Review>reviews= reviewService.getUnapprovedReviews();
       return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

    @Operation(summary = "approve Review",description = "Confirmation of the comment given by the admin")
    @PutMapping("/v1/approveReview")
    public ResponseEntity<?> approveReview(@RequestParam  Long reviewId) {
            Review review = reviewService.approveReviewById(reviewId);
            return new ResponseEntity<>("Review has been approved", HttpStatus.OK);

    }

    @Operation(summary = "reject  Review",description = "Rejection or disapproval of the comment by the admin")
    @PutMapping("/v1/rejectReview")
    public ResponseEntity<?> rejectReview(@RequestParam  Long reviewId) {
        Review review = reviewService.rejectReviewById(reviewId);
        return new ResponseEntity<>("Review has been rejected", HttpStatus.OK);

    }
}



