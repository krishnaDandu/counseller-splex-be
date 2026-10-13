package com.si.demo.service;

import com.si.demo.entity.Course;
import com.si.demo.repo.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {


    private final CourseRepo courseRepo;

    public CourseServiceImpl(CourseRepo courseRepo){
        this.courseRepo = courseRepo;
    }

    @Override
    public List<Course> getAllCourses(){
        return this.courseRepo.findAll();
    }
}
