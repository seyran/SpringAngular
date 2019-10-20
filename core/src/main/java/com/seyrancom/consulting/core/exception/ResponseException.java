package com.seyrancom.consulting.core.exception;

import org.springframework.http.HttpStatus;

public class ResponseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    private final HttpStatus httpStatus;

    public ResponseException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
