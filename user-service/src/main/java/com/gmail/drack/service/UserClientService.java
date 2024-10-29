package com.gmail.drack.service;

import java.util.List;

import com.gmail.drack.commons.dto.response.notification.NotificationUserResponse;
import com.gmail.drack.commons.dto.response.user.UserResponse;

public interface UserClientService {
    UserResponse getUserResponseById(Long userId);
    List<NotificationUserResponse> getUsersWhichUsersSubscribed();
    List<Long> getUserIdsWhichUserSubscribed();
}
