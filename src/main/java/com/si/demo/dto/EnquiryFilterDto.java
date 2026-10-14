package com.si.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnquiryFilterDto {

    private String stuName;

    private String stuPhno;

    private Integer courseId;

    public EnquiryFilterDto(String stuName, String stuPhno, Integer courseId){
        this.stuName = stuName;
        this.stuPhno = stuPhno;
        this.courseId = courseId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPhno() {
        return stuPhno;
    }

    public void setStuPhno(String stuPhno) {
        this.stuPhno = stuPhno;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
