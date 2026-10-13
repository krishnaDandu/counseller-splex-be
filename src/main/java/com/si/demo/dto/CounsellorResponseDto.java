package com.si.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounsellorResponseDto {
    private Integer counsellorId;
    private String name;
    private String email;
    private String phno;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getCounsellorId() {
        return counsellorId;
    }

    public void setCounsellorId(Integer counsellorId) {
        this.counsellorId = counsellorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
