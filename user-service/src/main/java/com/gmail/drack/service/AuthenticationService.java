package com.gmail.drack.service;

import java.util.Map;

import org.springframework.validation.BindingResult;

import com.gmail.drack.dto.request.AuthenticationRequest;
import com.gmail.drack.repository.UserPrincipalProjection;
import com.gmail.drack.repository.projection.AuthUserProjection;

public interface AuthenticationService {
    Long getAuthenticatedUserId();   
    UserPrincipalProjection getUserPrincipalByEmail(String email);
    Map<String, Object> login(AuthenticationRequest request, BindingResult result);
    String getExistingEmail(String email, BindingResult result);
    String sendPasswordResetCode(String email, BindingResult result);
    AuthUserProjection getUserByPasswordResetCode(String code);
    String passwordReset(String email, String password, String password2, BindingResult result);
    String currentPasswordReset(String currentPassword, String password, String password2, BindingResult result);
}
