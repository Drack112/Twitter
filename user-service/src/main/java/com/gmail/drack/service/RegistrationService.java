package com.gmail.drack.service;

import com.gmail.drack.dto.request.RegistrationRequest;
import org.springframework.validation.BindingResult;

public interface RegistrationService {
    String registration(RegistrationRequest request, BindingResult bindingResult);
}
