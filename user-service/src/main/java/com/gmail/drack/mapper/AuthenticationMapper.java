package com.gmail.drack.mapper;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.gmail.drack.dto.request.AuthenticationRequest;
import com.gmail.drack.dto.request.CurrentPasswordResetRequest;
import com.gmail.drack.dto.request.PasswordResetRequest;
import com.gmail.drack.dto.response.AuthUserResponse;
import com.gmail.drack.dto.response.AuthenticationResponse;
import com.gmail.drack.repository.projection.AuthUserProjection;
import com.gmail.drack.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {
    private final ModelMapper modelMapper;
    private final AuthenticationService authenticationService;

    public AuthenticationResponse getUserByToken() {
        return getAuthenticationResponse(authenticationService.getUserByToken());
    }

    public AuthenticationResponse login(AuthenticationRequest request, BindingResult result) {
        return getAuthenticationResponse(authenticationService.login(request, result));
    }

    public String getExistingEmail(String email, BindingResult bindingResult) {
        return authenticationService.getExistingEmail(email, bindingResult);
    }

    public String sendPasswordResetCode(String email, BindingResult bindingResult) {
        return authenticationService.sendPasswordResetCode(email, bindingResult);
    }

    public AuthUserResponse getUserByPasswordResetCode(String code) {
        AuthUserProjection user = authenticationService.getUserByPasswordResetCode(code);
        return modelMapper.map(user, AuthUserResponse.class);
    }

    public String passwordReset(PasswordResetRequest request, BindingResult bindingResult) {
        return authenticationService.passwordReset(request.getEmail(), request.getPassword(), request.getPassword2(), bindingResult);
    }

    public String currentPasswordReset(CurrentPasswordResetRequest request, BindingResult bindingResult) {
        return authenticationService.currentPasswordReset(request.getCurrentPassword(), request.getPassword(),
                request.getPassword2(), bindingResult);
    }

    AuthenticationResponse getAuthenticationResponse(Map<String, Object> credentials) {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setUser(modelMapper.map(credentials.get("user"), AuthUserResponse.class));
        response.setToken((String) credentials.get("token"));
        return response;
    }
}
