package com.shahian.productreviewmanagement.service.impl;

import com.shahian.productreviewmanagement.model.dto.ProductDTO;
import com.shahian.productreviewmanagement.model.entity.Product;
import com.shahian.productreviewmanagement.repository.ProductRepository;
import com.shahian.productreviewmanagement.service.interfaces.ProductService;
import com.shahian.productreviewmanagement.service.interfaces.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
private final ReviewService reviewService;
    public ProductServiceImpl(ProductRepository productRepository, ReviewService reviewService) {
        this.productRepository = productRepository;
        this.reviewService = reviewService;
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProductById(Long id) {

        return null;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAllByIsDeletedFalse();
        List<ProductDTO> productDTOS = products.stream().map(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setReviewEnabled(product.isReviewEnabled());
            productDTO.setAverageRating(reviewService.calculateAverageRating(product.getId()));
            productDTO.setReviewCount(reviewService.getReviewCount(product.getId()));
            return productDTO;
        }).collect(Collectors.toList());
        return productDTOS;
    }
}
