package com.gmail.drack.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.drack.commons.constants.PathConstants;
import com.gmail.drack.dto.request.AuthenticationRequest;
import com.gmail.drack.dto.request.ProcessEmailRequest;
import com.gmail.drack.dto.response.AuthUserResponse;
import com.gmail.drack.dto.response.AuthenticationResponse;
import com.gmail.drack.mapper.AuthenticationMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathConstants.UI_V1_AUTH)
public class AuthenticationController {
    private final AuthenticationMapper authenticationMapper;
    
    @PostMapping(PathConstants.LOGIN)
    public ResponseEntity<AuthenticationResponse> login(
        @Valid
        @RequestBody
        AuthenticationRequest request,
        BindingResult result
    ) {
        return ResponseEntity.ok(authenticationMapper.login(request, result));
    }
    
    @PostMapping(PathConstants.FORGOT_EMAIL)
    public ResponseEntity<String> getExistingEmail(@Valid @RequestBody ProcessEmailRequest request, BindingResult result) {
        return ResponseEntity.ok(authenticationMapper.getExistingEmail(request.getEmail(), result));
    }

    @PostMapping(PathConstants.FORGOT)
    public ResponseEntity<String> sendPasswordResetCode(
        @Valid
        @RequestBody ProcessEmailRequest request,
        BindingResult bindingResult
    ) {
        return ResponseEntity.ok(authenticationMapper.sendPasswordResetCode(request.getEmail(), bindingResult));
    }

    @GetMapping(PathConstants.RESET_CODE)
    public ResponseEntity<AuthUserResponse> getUserByPasswordResetCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(authenticationMapper.getUserByPasswordResetCode(code));
    }
}
