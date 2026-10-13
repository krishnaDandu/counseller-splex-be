package com.si.demo.exception;

public class CounsellorNotFoundException extends RuntimeException{

    public CounsellorNotFoundException(String message){
        super(message);
    }
}
