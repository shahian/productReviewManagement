package com.shahian.productreviewmanagement.service.interfaces;

import com.shahian.productreviewmanagement.model.dto.ProductDTO;
import com.shahian.productreviewmanagement.model.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getProducts();
    Product getProductById(Long id);
    List<ProductDTO> getAllProducts();
}
