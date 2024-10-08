package com.gmail.drack.service.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.gmail.drack.exception.InputFieldException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserServiceHelper {

    public void processInputErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
    }
}
