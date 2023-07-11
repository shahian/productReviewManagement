package com.shahian.productreviewmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandeling {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleUserNotFoundException(UserNotFoundException ex){
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(GeneralFoundException.class)
    public ResponseEntity<ErrorResponse> handleGeneralFoundException(GeneralFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleProductNotFoundException(ProductNotFoundException ex){
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleReviewNotFoundException(ReviewNotFoundException ex){
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
