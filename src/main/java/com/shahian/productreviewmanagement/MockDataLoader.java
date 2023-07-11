package com.shahian.productreviewmanagement;

import com.shahian.productreviewmanagement.model.entity.Product;
import com.shahian.productreviewmanagement.model.entity.Review;
import com.shahian.productreviewmanagement.model.entity.User;
import com.shahian.productreviewmanagement.repository.ProductRepository;
import com.shahian.productreviewmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MockDataLoader implements ApplicationRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public MockDataLoader(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Create and insert mock data here
        insertMockData();
    }

    private void insertMockData() {
        // Create mock products
        List<Product>products=new ArrayList<>();
        Product product1 = Product.builder()
                .name("Product 1")
                .price(10.0)
                .reviewEnabled(true)
                .build();
        products.add(product1);

        Product product2 = Product.builder()
                .name("Product 2")
                .price(20.0)
                .reviewEnabled(true)
                .build();
        products.add(product2);

        Product product3 = Product.builder()
                .name("Product 3")
                .price(30.0)
                .reviewEnabled(true)
                .build();
        products.add(product3);
        // Create mock reviews
        Review review1 = Review.builder()
                .rating(4)
                .comment("Great product!")
                .vote(10)
                .approved(true)
                .build();

        Review review2 = Review.builder()
                .rating(5)
                .comment("Excellent product!")
                .vote(15)
                .approved(true)
                .build();
        Review review3 = Review.builder()
                .rating(1)
                .comment("bad product!")
                .vote(3)
                .approved(false)
                .build();
        Review review4 = Review.builder()
                .rating(8)
                .comment("Excellent product!")
                .vote(11)
                .approved(true)
                .build();
        Review review5 = Review.builder()
                .rating(6)
                .comment("good product!")
                .vote(7)
                .approved(true)
                .build();
        // Create mock users
        User user1 = User.builder()
                .fullName("User 1")
                .build();

        User user2 = User.builder()
                .fullName("User 2")
                .build();

        // Associate reviews with products and users
        List<Review> product1Reviews = new ArrayList<>();
        product1Reviews.add(review1);
        product1Reviews.add(review4);
        product1Reviews.add(review5);
        product1.setReviews(product1Reviews);
        review1.setProduct(product1);
        review4.setProduct(product1);
        review5.setProduct(product1);


        List<Review> product2Reviews = new ArrayList<>();
        product2Reviews.add(review2);
        product2Reviews.add(review3);
        product2.setReviews(product2Reviews);
        review2.setProduct(product2);
        review3.setProduct(product2);

        review1.setUser(user1);
        review4.setUser(user1);
        review5.setUser(user1);

        review2.setUser(user2);
        review3.setUser(user2);
        // Save entities to the database
        userRepository.save(user1);
        userRepository.save(user2);
        productRepository.saveAll(products);
    }
}