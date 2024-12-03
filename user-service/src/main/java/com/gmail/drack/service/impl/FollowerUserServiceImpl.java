package com.gmail.drack.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gmail.drack.broker.producer.FollowRequestUserProducer;
import com.gmail.drack.broker.producer.FollowUserNotificationProducer;
import com.gmail.drack.broker.producer.FollowUserProducer;
import com.gmail.drack.model.User;
import com.gmail.drack.repository.FollowerUserRepository;
import com.gmail.drack.repository.UserRepository;
import com.gmail.drack.repository.projection.BaseUserProjection;
import com.gmail.drack.repository.projection.FollowerUserProjection;
import com.gmail.drack.repository.projection.UserProjection;
import com.gmail.drack.service.AuthenticationService;
import com.gmail.drack.service.FollowerUserService;
import com.gmail.drack.service.utils.UserServiceHelper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowerUserServiceImpl implements FollowerUserService {

    private final UserRepository userRepository;
    private final UserServiceHelper userServiceHelper;
    private final FollowerUserRepository followerUserRepository;
    private final AuthenticationService authenticationService;
    private final FollowUserProducer  followUserProducer;
    private final FollowUserNotificationProducer followUserNotificationProducer;
    private final FollowRequestUserProducer followRequestUserProducer;

    @Override
    public Page<UserProjection> getFollowers(Long userId, Pageable pageable) {
        userServiceHelper.validateUserProfile(userId);
        return followerUserRepository.getFollowersById(userId, pageable);
    }

    @Override
    public Page<UserProjection> getFollowing(Long userId, Pageable pageable) {
        userServiceHelper.validateUserProfile(userId);
        return followerUserRepository.getFollowingById(userId, pageable);
    }

    @Override
    public Page<FollowerUserProjection> getFollowersRequest(Pageable pageable) {
        Long authUserId = authenticationService.getAuthenticatedUserId();
        return followerUserRepository.getFollowerRequests(authUserId, pageable);
    }

    @Override
    @Transactional
    public Boolean processFollow(Long userId) {
        User user = userServiceHelper.getUserById(userId);
        User authUser = authenticationService.getAuthenticatedUser();
        userServiceHelper.checkIsUserBlocked(userId);

        if(followerUserRepository.isFollower(authUser.getId(), user.getId())) {
            followerUserRepository.unfollow(authUser.getId(), user.getId());
            followerUserRepository.updateFollowingCount(false, user.getId());
            followerUserRepository.updateFollowersCount(false, authUser.getId());
            userRepository.unsubscribe(authUser.getId(), user.getId());
            followUserProducer.sendFollowUserEvent(user, authUser.getId(), false);
        }else {
            if(!user.isPrivateProfile()) {
                followerUserRepository.follow(authUser.getId(), user.getId());
                followerUserRepository.updateFollowingCount(true, user.getId());
                followerUserRepository.updateFollowersCount(true, authUser.getId());
                followUserNotificationProducer.sendFollowUserNotificationEvent(authUser, user);
                followUserProducer.sendFollowUserEvent(user, authUser.getId(), true);
                return true;
            }else {
                followerUserRepository.addFollowerRequest(authUser.getId(), user.getId());
                followerUserRepository.updateFollowerRequestsCount(true, user.getId());
                followRequestUserProducer.sendFollowRequestUserEvent(user, authUser.getId(), true);
            }
        }

        return false;
    }

    @Override
    @Transactional
    public List<BaseUserProjection> overallFollowers(Long userId) {
        userServiceHelper.validateUserProfile(userId);
        Long authUserId = authenticationService.getAuthenticatedUserId();
        return followerUserRepository.getSameFollowers(userId, authUserId, BaseUserProjection.class);
    }
}
