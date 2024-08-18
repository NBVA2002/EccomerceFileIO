package com.example.fileservice.exception;

import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
@Slf4j(topic = "exception")
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(new ExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(new ExceptionResponse(e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_GATEWAY.value()));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ExceptionResponse> handleDateTimeParseException(DateTimeParseException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(new ExceptionResponse(e.getMessage(), HttpStatus.BAD_GATEWAY.value()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> handleUnauthorizedException(Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
    }

}
