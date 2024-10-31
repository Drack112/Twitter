package com.gmail.drack.service.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.gmail.drack.exception.InputFieldException;
import com.gmail.drack.repository.BlockUserRepository;
import com.gmail.drack.repository.FollowerUserRepository;
import com.gmail.drack.repository.UserRepository;
import com.gmail.drack.service.AuthenticationService;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserServiceHelper {

    @Lazy
    private final AuthenticationService authenticationService;
    private final BlockUserRepository blockUserRepository;
    private final UserRepository userRepository;
    private final FollowerUserRepository followerUserRepository;

    public void processInputErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
    }

    public boolean isUserBlockedByMyProfile(Long userId) {
        Long authUserId = authenticationService.getAuthenticatedUserId();
        return blockUserRepository.isUserBlocked(authUserId, userId);
    }

    public boolean isMyProfileBlockedByUser(Long userId) {
        Long authUserId = authenticationService.getAuthenticatedUserId();
        return blockUserRepository.isUserBlocked(userId, authUserId);
    }

    public boolean isMyProfileWaitingForApprove(Long userId) {
        Long authUserId = authenticationService.getAuthenticatedUserId();
        return userRepository.isMyProfileWaitingForApprove(userId, authUserId);
    }

    public boolean isUserFollowByOtherUser(Long userId) {
        Long authUserId = authenticationService.getAuthenticatedUserId();
        return followerUserRepository.isUserFollowByOtherUser(authUserId, userId);
    }
}
