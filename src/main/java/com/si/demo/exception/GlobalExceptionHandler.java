
        package com.si.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Course not found
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCourseNotFound(
            CourseNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    // Counsellor not found
    @ExceptionHandler(CounsellorNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCounsellorNotFound(
            CounsellorNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    // Other expected runtime errors
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(
            RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "The request could not be processed."));
    }

    // Unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(
            Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "message",
                        "An unexpected error occurred. Please try again."
                ));
    }
}


//package com.si.demo.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException runExp){
//
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(Map.of("message", runExp.getMessage()));
//
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, Object>> handleException(){
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Failed to save enquiry. Please try again.");
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    }
//
//    @ExceptionHandler(CounsellorNotFoundException.class)
//    public ResponseEntity<Map<String, String>> handleCounsellorNotFound(
//            CounsellorNotFoundException ex) {
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(Map.of("message", ex.getMessage()));
//    }
//
////    @ExceptionHandler(DuplicateEmailException.class)
////    public ResponseEntity<Map<String, String>> handleDuplicateEmail(
////            DuplicateEmailException ex) {
////
////        return ResponseEntity
////                .status(HttpStatus.CONFLICT)
////                .body(Map.of("message", ex.getMessage()));
////    }
////
////    @ExceptionHandler(InvalidCredentialsException.class)
////    public ResponseEntity<Map<String, String>> handleInvalidCredentials(
////            InvalidCredentialsException ex) {
////
////        return ResponseEntity
////                .status(HttpStatus.UNAUTHORIZED)
////                .body(Map.of("message", ex.getMessage()));
////    }
////
////    @ExceptionHandler(CourseNotFoundException.class)
////    public ResponseEntity<Map<String, String>> handleCourseNotFound(
////            CourseNotFoundException ex) {
////
////        return ResponseEntity
////                .status(HttpStatus.NOT_FOUND)
////                .body(Map.of("message", ex.getMessage()));
////    }
//
//
//}
