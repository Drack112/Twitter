package com.gmail.drack.commons.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gmail.drack.commons.constants.PathConstants;
import com.gmail.drack.commons.exceptions.ApiRequestException;
import com.gmail.drack.commons.constants.ErrorMessage;

import jakarta.servlet.http.HttpServletRequest;

public class AuthUtil {
    public static Long getAuthenticatedUserId() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
        String userID = request.getHeader(PathConstants.AUTH_USER_ID_HEADER);

        if(userID == null) {
            throw new ApiRequestException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }else {
            return Long.parseLong(userID);
        }
    }
}
