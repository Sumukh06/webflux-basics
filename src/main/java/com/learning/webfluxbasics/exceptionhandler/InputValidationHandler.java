package com.learning.webfluxbasics.exceptionhandler;


import com.learning.webfluxbasics.dto.InputFailedValidation;
import com.learning.webfluxbasics.exceptions.InputValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {
    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidation> handleException(InputValidationException ex){
        InputFailedValidation response=new InputFailedValidation();
        response.setInput(ex.getInput());
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
