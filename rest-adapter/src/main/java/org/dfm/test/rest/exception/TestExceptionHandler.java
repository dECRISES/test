package org.dfm.test.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.dfm.test.domain.exception.TestNotFoundException;

@RestControllerAdvice(basePackages = {"org.dfm.test"})
public class TestExceptionHandler {

  @ExceptionHandler(value = TestNotFoundException.class)
  public final ResponseEntity<TestExceptionResponse> handleTestNotFoundException(final WebRequest request) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TestExceptionResponse.builder().message("Test not found").path(((ServletWebRequest) request).getRequest().getRequestURI()).build());
  }
}
