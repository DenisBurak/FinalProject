package com.zemelya.controller.exception;

import com.zemelya.exception.TooManyRequestException;
import com.zemelya.util.UUIDGenerator;
import org.apache.log4j.Logger;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class DefaultExceptionHandler {

  private static final Logger log =
      Logger.getLogger(com.zemelya.controller.exception.DefaultExceptionHandler.class);

  @ExceptionHandler({
    EmptyResultDataAccessException.class,
    NoSuchElementException.class,
    EntityNotFoundException.class,
    ConversionFailedException.class,
    UsernameNotFoundException.class
  })
  public ResponseEntity<Object> handlerEntityNotFoundException(Exception e) {

    ErrorContainer error = createError(2, e.getMessage(), e);

    return new ResponseEntity<>(Collections.singletonMap("error", error), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({
          EntityExistsException.class})
  public ResponseEntity<Object> handlerEntityExistsException(EntityExistsException e) {

    ErrorContainer error = createError(3, e.getMessage(), e);

    return new ResponseEntity<>(Collections.singletonMap("error", error), HttpStatus.FOUND);
  }

  @ExceptionHandler({
          TooManyRequestException.class})
  public ResponseEntity<Object> handlerTooManyRequestException(TooManyRequestException e) {

    ErrorContainer error = createError(e.getErrorCode(), e.getCustomMessage(), e);

    return new ResponseEntity<>(Collections.singletonMap("error", error), HttpStatus.TOO_MANY_REQUESTS);
  }

  @ExceptionHandler({
          NumberFormatException.class,
          HttpMessageNotReadableException.class})
  public ResponseEntity<Object> handlerNumberFormatException(Exception e) {

    ErrorContainer error = createError(3, e.getMessage(), e);

    return new ResponseEntity<>(Collections.singletonMap("error", error), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> сonstraintViolationException(Exception e) {
    ErrorContainer error = createError(1, e.getMessage(), e);
    return new ResponseEntity<>(Collections.singletonMap("error", error), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ModelMap handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
    List<FieldError> errors = error.getBindingResult().getFieldErrors();
    ModelMap map = new ModelMap();
    ModelMap errorMap = new ModelMap();
    map.addAttribute("hasErrors", true);
    for (FieldError fieldError : errors) {
      errorMap.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
    }
    map.addAttribute("bindingErrors", errorMap);
    return map;
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
