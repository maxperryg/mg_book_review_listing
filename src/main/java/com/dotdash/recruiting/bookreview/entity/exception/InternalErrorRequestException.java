package com.dotdash.recruiting.bookreview.entity.exception;

public class InternalErrorRequestException extends RequestException{
    public InternalErrorRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalErrorRequestException(String message) {
        super(message);
    }
}
