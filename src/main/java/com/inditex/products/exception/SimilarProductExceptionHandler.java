package com.inditex.products.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class SimilarProductExceptionHandler {

    @Value("${msg.error.404}")
    private  String MSG_ERROR_NOT_FOUND;
    @Value("${msg.error.500}")
    private  String MSG_ERROR_INTERNAL_SERVICE_ERROR;

    @ExceptionHandler(value = ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleProductNotFoundException(final ProductNotFoundException ex) {
        log.debug(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MSG_ERROR_NOT_FOUND);
    }

    @ExceptionHandler(value = ProductException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleProductException(final ProductException ex) {
        log.debug(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MSG_ERROR_INTERNAL_SERVICE_ERROR);
    }
}
