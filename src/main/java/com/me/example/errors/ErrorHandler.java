package com.me.example.errors;

import com.me.example.dtos.ErrorResponse;
import com.me.example.errors.exceptions.DataNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorDetail> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorDetail(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ErrorCodes.INVALID_REQUEST)
                .message("Validation failed")
                .errors(errors)
                .build();

        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        List<ErrorDetail> errors = e.getConstraintViolations().stream()
                .map(constraintViolation -> new ErrorDetail(
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage()
                ))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ErrorCodes.INVALID_REQUEST)
                .message("Validation failed")
                .errors(errors)
                .build();

        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ErrorDetail detail = new ErrorDetail(
                e.getName(),
                e.getName() + " should be of type " + Objects.requireNonNull(e.getRequiredType()).getSimpleName()
        );

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ErrorCodes.INVALID_REQUEST)
                .message("Validation failed")
                .errors(Collections.singletonList(detail))
                .build();

        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ErrorCodes.DATA_NOT_FOUND)
                .message(e.getMessage())
                .errors(Collections.emptyList())
                .build();

        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }
}
