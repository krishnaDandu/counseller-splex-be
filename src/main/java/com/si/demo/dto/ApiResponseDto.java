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

    public ApiResponseDto() {
       // super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
