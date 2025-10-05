package com.example.Product.ExceptionHandler;

import com.example.Product.Exception.ProductPurchaseException;

import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> ProductNotFoundExceptionHandler(ProductPurchaseException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    } @ExceptionHandler(EntityActionVetoException.class)
    public ResponseEntity<String> EntityNotFoundExceptionHandler(EntityActionVetoException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle (MethodArgumentNotValidException e) {
        var errors= new HashMap<String, String>();
        e.getBindingResult().getFieldErrors().forEach((

                error) -> {
            var fieldName=((FieldError)error).getField();
            var errorMessage=((FieldError)error).getDefaultMessage();
            errors.put(fieldName,errorMessage);

        });
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(errors));
    }
}
