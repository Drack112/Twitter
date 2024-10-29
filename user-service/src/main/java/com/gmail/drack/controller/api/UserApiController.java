package com.gmail.drack.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.drack.commons.constants.PathConstants;
import com.gmail.drack.commons.dto.response.notification.NotificationUserResponse;
import com.gmail.drack.commons.dto.response.user.UserResponse;
import com.gmail.drack.service.UserClientService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
@RequestMapping(PathConstants.API_V1_USER)
public class UserApiController {
    private final UserClientService userService;

    
    @GetMapping(PathConstants.USER_ID)
    public UserResponse getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserResponseById(userId);
    }

    @GetMapping(PathConstants.SUBSCRIBERS)
    public List<NotificationUserResponse> getUsersWhichUsersSubscribed() {
        return userService.getUsersWhichUsersSubscribed();
    }

    @GetMapping(PathConstants.SUBSCRIBERS_IDS)
    public List<Long> getUserIdsWhichUserSubscribed() {
        return userService.getUserIdsWhichUserSubscribed();
    }
}
