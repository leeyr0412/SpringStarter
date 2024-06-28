package com.study.springStarter.common.exception;

import com.study.springStarter.common.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
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

    @ExceptionHandler(BadCredentialsException.class)
    public BaseResponse<?> badCredentialsException(BadCredentialsException ex) {
        Map<String, String> errors = new HashMap<>();
        String errorMessage = ex.getMessage();
        errors.put("로그인 실패", "입력 정보를 확인하세요. " + errorMessage);

        return BaseResponse.error(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public BaseResponse<?> accessDeniedException(AccessDeniedException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("요청 실패", "토큰 정보가 잘못되었습니다.");

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
