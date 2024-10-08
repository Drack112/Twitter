package com.gmail.drack.mapper;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.gmail.drack.dto.request.RegistrationRequest;
import com.gmail.drack.service.RegistrationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegistrationMapper {
    private final RegistrationService registrationService;

    public String registration(RegistrationRequest request, BindingResult bindingResult) {
        return registrationService.registration(request, bindingResult);
    }

}
