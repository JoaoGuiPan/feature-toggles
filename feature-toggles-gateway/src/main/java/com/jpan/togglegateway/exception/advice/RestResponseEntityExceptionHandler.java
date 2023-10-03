package com.jpan.togglegateway.exception.advice;

import com.jpan.togglegateway.exception.BeNeTooManyRequestsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BeNeTooManyRequestsException.class})
    protected ResponseEntity<String> beneTooManyRequests(BeNeTooManyRequestsException ex, WebRequest request) {
        final String body = "Too Many Requests!";
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).header(HttpHeaders.RETRY_AFTER, String.valueOf(ex.getRetryAfter())).body(body);
    }
}