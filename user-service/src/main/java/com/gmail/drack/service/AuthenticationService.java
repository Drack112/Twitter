package com.gmail.drack.service;

import java.util.Map;

import org.springframework.validation.BindingResult;

import com.gmail.drack.model.User;
import com.gmail.drack.repository.projection.AuthUserProjection;
import com.gmail.drack.repository.projection.UserPrincipalProjection;

public interface AuthenticationService {
    Long getAuthencatedUserId();

    User getAuthenticatedUser();

    UserPrincipalProjection getUserPrincipalByEmail(String email);

    Map<String, Object> getUserByToken();

    String getExistingEmail(String email, BindingResult bindingResult);

    String sendPasswordResetCode(String email, BindingResult bindingResult);

    AuthUserProjection getUserByPasswordResetCode(String code);

    String passwordReset(String email, String password, String password2, BindingResult bindingResult);

    String currentPasswordAndReset(String currentPassword, String password, String password2,
            BindingResult bindingResult);
}
