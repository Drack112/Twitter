package com.gmail.drack.service.impl;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gmail.drack.broker.producer.SendEmailProducer;
import com.gmail.drack.commons.constants.PathConstants;
import com.gmail.drack.commons.event.SendEmailEvent;
import com.gmail.drack.commons.exceptions.ApiRequestException;
import com.gmail.drack.commons.security.JwtProvider;
import com.gmail.drack.constants.UserErrorMessage;
import com.gmail.drack.constants.UserSuccessMessage;
import com.gmail.drack.dto.request.AuthenticationRequest;
import com.gmail.drack.model.UserRole;
import com.gmail.drack.repository.UserPrincipalProjection;
import com.gmail.drack.repository.UserRepository;
import com.gmail.drack.repository.projection.AuthUserProjection;
import com.gmail.drack.repository.projection.UserCommonProjection;
import com.gmail.drack.service.AuthenticationService;
import com.gmail.drack.service.utils.UserServiceHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserServiceHelper userServiceHelper;
    private final JwtProvider jwtProvider;
    private final SendEmailProducer sendEmailProducer;

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
        RequestAttributes attribs = RequestContextHolder.getRequestAttributes();

        @SuppressWarnings("null")
        HttpServletRequest request = ((ServletRequestAttributes) attribs).getRequest();

        return Long.parseLong(request.getHeader(PathConstants.AUTH_USER_ID_HEADER));
    }

    @Override
    public Map<String, Object> login(AuthenticationRequest request, BindingResult result) {
        userServiceHelper.processInputErrors(result);
        AuthUserProjection user = userRepository.getUserByEmail(request.getEmail(), AuthUserProjection.class)
        .orElseThrow(() -> new ApiRequestException(UserErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND));   
        String token = jwtProvider.createToken(user.getEmail(), UserRole.USER.name());
        return Map.of("user", user, "token", token);     
    }

    @Override
    public String getExistingEmail(String email, BindingResult result) {
        userServiceHelper.processInputErrors(result);
        userRepository.getUserByEmail(email, UserCommonProjection.class)
        .orElseThrow(() -> new ApiRequestException(UserErrorMessage.EMAIL_NOT_FOUND, HttpStatus.NOT_FOUND));
        return UserSuccessMessage.RESET_PASSWORD_CODE_IS_SEND;
    }

    @Override
    @Transactional
    public String sendPasswordResetCode(String email, BindingResult bindingResult) {
        userServiceHelper.processInputErrors(bindingResult);
        UserCommonProjection user = userRepository.getUserByEmail(email, UserCommonProjection.class)
                .orElseThrow(() -> new ApiRequestException(UserErrorMessage.EMAIL_NOT_FOUND, HttpStatus.NOT_FOUND));
        userRepository.updatePasswordResetCode(UUID.randomUUID().toString().substring(0, 7), user.getId());
        String passwordResetCode = userRepository.getPasswordResetCode(user.getId());
        SendEmailEvent sendEmailEvent = SendEmailProducer.toSendPasswordResetEmailEvent(user.getEmail(), user.getFullName(), passwordResetCode);
        sendEmailProducer.sendEmail(sendEmailEvent);
        return UserSuccessMessage.RESET_PASSWORD_CODE_IS_SEND;
    }

    @Override
    public AuthUserProjection getUserByPasswordResetCode(String code) {
        return userRepository.getByPasswordResetCode(code)
        .orElseThrow(() -> new ApiRequestException(UserErrorMessage.INVALID_PASSWORD_RESET_CODE, HttpStatus.BAD_REQUEST));
    }

}
