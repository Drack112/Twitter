package com.gmail.drack.commons.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowUserNotificationEvent {
    private boolean notificationCondition;
    private UserNotificationDTO notifiedUser;
    private UserNotificationDTO user;
    private UserNotificationDTO userToFollow;
}