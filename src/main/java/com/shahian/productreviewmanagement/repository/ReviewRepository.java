package com.shahian.productreviewmanagement.repository;

import com.shahian.productreviewmanagement.model.entity.Review;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review>findAllByProductIdAndIsDeletedFalse(Long productId);
    Long countByProductIdAndIsDeletedFalse(Long productId);
    Optional<Review> findByIdAndIsDeletedFalse(Long reviewId);
    List<Review>findAllByApprovedFalseAndIsDeletedFalse();
}
