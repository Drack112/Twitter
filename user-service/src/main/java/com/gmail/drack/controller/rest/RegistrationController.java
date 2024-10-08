package com.gmail.drack.controller.rest;

import com.gmail.drack.commons.constants.PathConstants;
import com.gmail.drack.dto.request.RegistrationRequest;
import com.gmail.drack.mapper.RegistrationMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathConstants.UI_V1_AUTH)
public class RegistrationController {
    private final RegistrationMapper registrationMapper;

    @PostMapping(PathConstants.REGISTRATION_CHECK)
    public ResponseEntity<String> registration(@Valid @RequestBody RegistrationRequest request,
            BindingResult bindingResult) {
        return ResponseEntity.ok(registrationMapper.registration(request, bindingResult));
    }

}
