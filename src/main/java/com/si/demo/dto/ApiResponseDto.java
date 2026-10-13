package com.si.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ApiResponseDto<T> {

    private String message;
    private T data;

    public ApiResponseDto(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
