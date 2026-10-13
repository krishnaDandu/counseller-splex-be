package com.si.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnquiryRequestDto {

//    private Integer enqId;
    private String stuName;
    private String stuPhno;
    private String classMode;
    private String enqStatus;
    private Integer courseId;
 //   private Integer counsellorId;


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

    public String getClassMode() {
        return classMode;
    }

    public void setClassMode(String classMode) {
        this.classMode = classMode;
    }

    public String getEnqStatus() {
        return enqStatus;
    }

    public void setEnqStatus(String enqStatus) {
        this.enqStatus = enqStatus;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
