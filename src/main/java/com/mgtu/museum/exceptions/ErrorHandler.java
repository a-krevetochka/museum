package com.mgtu.museum.exceptions;

import com.mgtu.museum.exceptions.business.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({BusinessException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleBusinessException(Exception exception) {
        String message;

        if (exception instanceof BusinessException) {
            message = exception.getMessage();
        } else {
            message = exception.getMessage();
        }

        return ResponseEntity.badRequest().body(message);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessException(AccessDeniedException exception) {
        return ResponseEntity.status(403).body(exception.getMessage());
    }
}
