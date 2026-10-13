package com.si.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnquiryFilterDto {

    private String stuName;

    private String stuPhno;

    private List<Integer> courseIds;
}
