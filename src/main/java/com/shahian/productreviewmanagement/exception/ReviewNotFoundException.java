package com.shahian.productreviewmanagement.exception;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
