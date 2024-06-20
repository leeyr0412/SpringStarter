package com.study.springStarter.common.exception;

import com.study.springStarter.common.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage != null ? errorMessage : "Invalid Input");
        });
        return BaseResponse.error(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(InvalidInputException.class)
    public BaseResponse<?> invalidInputException(InvalidInputException ex) {
        Map<String, String> errors = new HashMap<>();
        String errorMessage = ex.getMessage();
        errors.put(ex.getFieldName(), errorMessage != null ? errorMessage : "Invalid Input");

        return BaseResponse.error(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<?> defaultException(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        String errorMessage = ex.getMessage();
        errors.put("미처리 예외", errorMessage != null ? errorMessage : "Error");

        return BaseResponse.error(HttpStatus.BAD_REQUEST, errors);
    }
}
