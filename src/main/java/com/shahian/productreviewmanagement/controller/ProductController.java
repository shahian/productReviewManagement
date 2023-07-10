package com.shahian.productreviewmanagement.controller;

import com.shahian.productreviewmanagement.model.dto.ProductDTO;
import com.shahian.productreviewmanagement.model.entity.Product;
import com.shahian.productreviewmanagement.service.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>>getAllProducts(){
        List<ProductDTO> productDTOs =productService.getAllProducts();
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }
}
