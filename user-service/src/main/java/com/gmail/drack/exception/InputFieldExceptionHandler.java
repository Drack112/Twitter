package com.gmail.drack.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputFieldExceptionHandler {
    @ExceptionHandler(InputFieldException.class)
    public ResponseEntity<Map<String, String>> handleInputFieldExceitpion(InputFieldException exception) {
        InputFieldException inputFieldException;

        if (exception.getBindingResult() != null) {
            inputFieldException = new InputFieldException(exception.getBindingResult());
        } else {
            inputFieldException = new InputFieldException(exception.getStatus(), exception.getErrorsMap());
        }

        return ResponseEntity.status(inputFieldException.getStatus()).body(inputFieldException.getErrorsMap());
    }
}