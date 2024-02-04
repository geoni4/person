package com.cos.person.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class MyExceptionHandler {
  
  @ExceptionHandler(value=IllegalArgumentException.class)
  public String arguException(IllegalArgumentException e){
    return e.getMessage();
  }
}
