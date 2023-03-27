package com.dotdash.recruiting.bookreview.entity.exception;

public class RequestException extends RuntimeException{
    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestException(String message) {
        super(message);
    }
}
