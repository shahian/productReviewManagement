package com.shahian.productreviewmanagement.repository;

import com.shahian.productreviewmanagement.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product>findByIdAndIsDeletedFalse(Long id);
    List<Product>findAllByIsDeletedFalse();
}
