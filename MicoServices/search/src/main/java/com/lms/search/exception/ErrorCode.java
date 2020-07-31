package com.lms.search.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_NOT_FOUND("USER_NOT_FOUND", HttpStatus.NOT_FOUND),
    INVALID_HEADER("INVALID_TOKEN", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("INVALID_TOKEN", HttpStatus.UNAUTHORIZED),
    INVALID_LOGIN_DETAILS("INVALID_LOGIN_DETAILS", HttpStatus.UNAUTHORIZED),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_REQUEST("INVALID_REQUEST", HttpStatus.BAD_REQUEST);

    String code;
    HttpStatus httpStatus;

    ErrorCode(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
