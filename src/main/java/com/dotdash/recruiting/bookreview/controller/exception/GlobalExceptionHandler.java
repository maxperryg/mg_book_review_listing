package com.dotdash.recruiting.bookreview.controller.exception;

import com.dotdash.recruiting.bookreview.entity.exception.BadRequestException;
import com.dotdash.recruiting.bookreview.entity.exception.GeneralErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {
            Exception.class
    })
    private ResponseEntity<GeneralErrorResponse> handleInternalError(Exception e) {
        return new ResponseEntity<>(
                new GeneralErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        "",
                        HttpStatus.INTERNAL_SERVER_ERROR.toString()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<GeneralErrorResponse> handleMissingParams(MissingServletRequestParameterException e) {
        return new ResponseEntity<>(
                new GeneralErrorResponse(
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST.toString()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<GeneralErrorResponse> returnBadRequest(BadRequestException e) {
        return new ResponseEntity<>(
                new GeneralErrorResponse(
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST.toString()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
