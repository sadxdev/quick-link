package com.bashverse.quicklink.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleAll(Exception ex) {
    return ResponseEntity.status(400).body(Map.of("error", ex.getMessage()));
  }
}
