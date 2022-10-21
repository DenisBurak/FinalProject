package com.zemelya.controller.exception;

import com.zemelya.exception.NoSuchEntityException;
import com.zemelya.util.UUIDGenerator;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.NoSuchElementException;

@ControllerAdvice
public class DefaultExceptionHandler {

  private static final Logger log =
      Logger.getLogger(com.zemelya.controller.exception.DefaultExceptionHandler.class);

  @ExceptionHandler({
    NoSuchEntityException.class,
    EmptyResultDataAccessException.class,
    NoSuchElementException.class
  })
  public ResponseEntity<Object> handlerEntityNotFoundException(Exception e) {

    ErrorContainer error = createError(2, e.getMessage(), e);

    return new ResponseEntity<>(Collections.singletonMap("error", error), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({NumberFormatException.class})
  public ResponseEntity<Object> handlerNumberFormatException(Exception e) {

    ErrorContainer error = createError(3, e.getMessage(), e);

    return new ResponseEntity<>(Collections.singletonMap("error", error), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handlerException(Exception e) {

    ErrorContainer error = createError(1, "General error", e);

    return new ResponseEntity<>(
        Collections.singletonMap("error", error), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ErrorContainer createError(int errorCode, String errorMessage, Exception e) {
    ErrorContainer error =
        ErrorContainer.builder()
            .exceptionId(UUIDGenerator.generateUUID())
            .errorCode(errorCode)
            .errorMessage(errorMessage)
            .e(e.getClass().toString())
            .build();

    log.error(error, e);
    log.info(error, e);

    return error;
  }
}
