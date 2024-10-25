package com.gmail.drack.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gmail.drack.commons.constants.PathConstants;
import com.gmail.drack.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    @Override
    public Long getAuthenticatedUserId() {
        return getUserId();
    }

    private Long getUserId() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
        return Long.parseLong(request.getHeader(PathConstants.AUTH_USER_ID_HEADER));
    }
}
