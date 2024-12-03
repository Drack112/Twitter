package com.gmail.drack.mapper;
import com.gmail.drack.commons.event.*;
import com.gmail.drack.commons.mapper.BasicMapper;
import com.gmail.drack.model.User;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProducerMapper {

    private final BasicMapper basicMapper;

    public UpdateUserEvent toUpdateUserEvent(User user) {
        return basicMapper.convertToResponse(user, UpdateUserEvent.class);
    }

    public BlockUserEvent toBlockUserEvent(User user, boolean hasUserBlocked) {
        BlockUserEvent blockUserEvent = basicMapper.convertToResponse(user, BlockUserEvent.class);
        blockUserEvent.setUserBlocked(hasUserBlocked);
        return blockUserEvent;
    }

    public FollowRequestUserEvent toFollowRequestUserEvent(User user, boolean hasUserFollowRequest) {
        FollowRequestUserEvent followRequestUserEvent = basicMapper.convertToResponse(user, FollowRequestUserEvent.class);
        followRequestUserEvent.setUserFollowRequest(hasUserFollowRequest);
        return followRequestUserEvent;
    }

    public FollowUserNotificationEvent toUserNotificationEvent(User authUser, User notifiedUser) {
        return FollowUserNotificationEvent.builder()
                .user(toUserDto(authUser))
                .userToFollow(toUserDto(notifiedUser))
                .notifiedUser(toUserDto(notifiedUser))
                .build();
    }

    public FollowUserEvent toFollowUserEvent(User user, boolean hasUserFollowed) {
        FollowUserEvent followUserEvent = basicMapper.convertToResponse(user, FollowUserEvent.class);
        followUserEvent.setUserFollow(hasUserFollowed);
        return followUserEvent;
    }

    private UserNotificationDTO toUserDto(User user) {
        return basicMapper.convertToResponse(user, UserNotificationDTO.class);
    }
}
