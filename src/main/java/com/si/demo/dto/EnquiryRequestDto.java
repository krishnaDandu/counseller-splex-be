package com.si.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.si.demo.entity.Course;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//@Setter
//@Getter
public class EnquiryRequestDto {

    private Integer enqId;
    private String stuName;
    private String stuPhno;
    private String classMode;
    private String enqStatus;
    private Integer courseId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime updatedAt;
 //   private Integer counsellorId;


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getEnqId() {        return enqId;    }

    public void setEnqId(Integer enqId) {        this.enqId = enqId;    }

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
