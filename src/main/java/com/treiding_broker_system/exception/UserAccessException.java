package com.treiding_broker_system.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserAccessException extends RuntimeException {
    private final HttpStatus httpStatus;

    public UserAccessException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
