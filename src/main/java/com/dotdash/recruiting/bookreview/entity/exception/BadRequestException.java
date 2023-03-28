package com.dotdash.recruiting.bookreview.entity.exception;

public class BadRequestException extends RequestException{
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message) {
        super(message);
    }
}
