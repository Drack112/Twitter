package com.gmail.drack.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

@Getter
@AllArgsConstructor
public class InputFieldException extends RuntimeException {
    private final HttpStatus status;
    private final Map<String, String> errorsMap;
    private BindingResult bindingResult;

    public InputFieldException(BindingResult bindingResult) {
        this.status = HttpStatus.BAD_REQUEST;
        this.errorsMap = handleErrors(bindingResult);
        this.bindingResult = bindingResult;
    }

    public InputFieldException(HttpStatus status, Map<String, String> errorsMap) {
        this.status = status;
        this.errorsMap = errorsMap;
    }

    private Map<String, String> handleErrors(BindingResult bindingResult) {
        Map<String, String> error = new HashMap<>();
        bindingResult.getFieldErrors()
                .forEach(fieldError -> error.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return error;
    }
}
