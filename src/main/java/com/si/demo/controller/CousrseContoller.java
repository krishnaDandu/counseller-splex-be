package com.si.demo.controller;

import com.si.demo.entity.Course;
import com.si.demo.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
//@CrossOrigin(origins = "http://localhost:4200")
public class CousrseContoller {

    private final CourseService courseService;

    public CousrseContoller(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> result = courseService.getAllCourses();
        return ResponseEntity.ok(result);
    }
}
