package com.example.weblibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

@RestControllerAdvice
public class EmployeeExceptions {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleNotFound(EmployeeNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("сотрудник с ID = %d не найден".formatted(e.getId()));
    }
    @ExceptionHandler
    public ResponseEntity<?> handleIOException(IOException ioException) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<?> handleSQLxception(SQLException sqlException) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception Exception) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
