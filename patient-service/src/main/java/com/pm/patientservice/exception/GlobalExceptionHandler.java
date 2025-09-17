package com.pm.patientservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String > errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(x-> errors.put(x.getField(),x.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAllReadyExistsException.class)
    public ResponseEntity<Map<String,String >> handleEmailAllReadyExistsException(EmailAllReadyExistsException ex){
        log.warn("email address alredy exists {}", ex.getMessage());

        Map<String,String> errors = new HashMap<>();
        errors.put("message", "email address already exist");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String,String >> handlePatientNotFoundException(PatientNotFoundException ex){
        log.warn("patient not found with id {} ", ex.getMessage());

        Map<String,String> errors = new HashMap<>();
        errors.put("message", "patient not found with the given id");
        return ResponseEntity.badRequest().body(errors);
    }

}
