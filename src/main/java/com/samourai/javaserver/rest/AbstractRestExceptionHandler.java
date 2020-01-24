package com.samourai.javaserver.rest;

import com.samourai.javaserver.exceptions.NotifiableException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public abstract class AbstractRestExceptionHandler extends ResponseEntityExceptionHandler {
  protected abstract Object handleError(NotifiableException e);

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleException(Exception e) {
    NotifiableException notifiable = NotifiableException.computeNotifiableException(e);
    Object restErrorResponse = handleError(notifiable);
    return new ResponseEntity<>(restErrorResponse, notifiable.getHttpStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException e,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return handleException(
        new NotifiableException("Invalid parameter: " + e.getParameter().getParameterName()));
  }
}
