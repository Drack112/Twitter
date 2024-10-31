package com.gmail.drack.service;

import java.util.Map;

import org.springframework.validation.BindingResult;

import com.gmail.drack.dto.request.AuthenticationRequest;
import com.gmail.drack.repository.UserPrincipalProjection;

public interface AuthenticationService {
    Long getAuthenticatedUserId();   
    UserPrincipalProjection getUserPrincipalByEmail(String email);
    Map<String, Object> login(AuthenticationRequest request, BindingResult result);
    String getExistingEmail(String email, BindingResult result);
}
