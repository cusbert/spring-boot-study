package com.study.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResponseMessage {

    // HttpStatus
    private String status;
    // Http Default Message
    private String message;
    // Error Message to USER
    private String errorMessage;
    // Error Code
    private String errorCode;
}
