package com.Ak.user_service.exception;

import com.Ak.user_service.payload_response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> ExceptionHandler(Exception ex,
                                                              WebRequest request){

ExceptionResponse response = ExceptionResponse.builder()
        .message(ex.getMessage())
        .error(request.getDescription(false))
        .timestamp(LocalDateTime.now())
        .build();
return ResponseEntity.ok(response);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionResponse> handleUserException(
            UserException ex,
            WebRequest request) {

        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .error(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
