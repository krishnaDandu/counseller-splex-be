package com.si.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException runExp){

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", runExp.getMessage()));

    }

    @ExceptionHandler(CounsellorNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCounsellorNotFound(
            CounsellorNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

//    @ExceptionHandler(DuplicateEmailException.class)
//    public ResponseEntity<Map<String, String>> handleDuplicateEmail(
//            DuplicateEmailException ex) {
//
//        return ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                .body(Map.of("message", ex.getMessage()));
//    }
//
//    @ExceptionHandler(InvalidCredentialsException.class)
//    public ResponseEntity<Map<String, String>> handleInvalidCredentials(
//            InvalidCredentialsException ex) {
//
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body(Map.of("message", ex.getMessage()));
//    }
//
//    @ExceptionHandler(CourseNotFoundException.class)
//    public ResponseEntity<Map<String, String>> handleCourseNotFound(
//            CourseNotFoundException ex) {
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(Map.of("message", ex.getMessage()));
//    }


}
