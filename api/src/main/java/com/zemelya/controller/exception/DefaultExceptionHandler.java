package com.zemelya.controller.exception;

import com.zemelya.controller.responce.ErrorMessage;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {
    private static final Logger log = Logger.getLogger(com.zemelya.controller.exception.DefaultExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
        /* Handles all other exceptions. Status code 500. */
        log.error(e.getMessage(), e);
        log.info(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(2L, e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
