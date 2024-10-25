package com.gmail.drack.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gmail.drack.commons.constants.PathConstants;
import com.gmail.drack.commons.exceptions.ApiRequestException;
import com.gmail.drack.constants.UserErrorMessage;
import com.gmail.drack.repository.UserPrincipalProjection;
import com.gmail.drack.repository.UserRepository;
import com.gmail.drack.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;

    @Override
    public Long getAuthenticatedUserId() {
        return getUserId();
    }

    @Override
    public UserPrincipalProjection getUserPrincipalByEmail(String email) {
        return userRepository.getUserByEmail(email, UserPrincipalProjection.class)
                .orElseThrow(() -> new ApiRequestException(UserErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    private Long getUserId() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
        return Long.parseLong(request.getHeader(PathConstants.AUTH_USER_ID_HEADER));
    }
}
