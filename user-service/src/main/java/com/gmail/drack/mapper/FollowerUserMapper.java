package com.gmail.drack.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gmail.drack.commons.dto.HeaderResponse;
import com.gmail.drack.commons.dto.response.user.UserResponse;
import com.gmail.drack.commons.mapper.BasicMapper;
import com.gmail.drack.dto.response.FollowerUserResponse;
import com.gmail.drack.repository.projection.FollowerUserProjection;
import com.gmail.drack.repository.projection.UserProjection;
import com.gmail.drack.service.FollowerUserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FollowerUserMapper {
    private final BasicMapper basicMapper;
    private final FollowerUserService followerUserService;

    public HeaderResponse<UserResponse> getFollowers(Long userId, Pageable pageable) {
        Page<UserProjection> users = followerUserService.getFollowers(userId, pageable);
        return basicMapper.getHeaderResponse(users, UserResponse.class);
    }

    public HeaderResponse<UserResponse> getFollowing(Long userId, Pageable pageable) {
        Page<UserProjection> users = followerUserService.getFollowing(userId, pageable);
        return basicMapper.getHeaderResponse(users, UserResponse.class);
    }

    public HeaderResponse<FollowerUserResponse> getFollowerRequests(Pageable pageable) {
        Page<FollowerUserProjection> followers = followerUserService.getFollowersRequest(pageable);
        return basicMapper.getHeaderResponse(followers, FollowerUserResponse.class);
    }

    public Boolean processFollow(Long userId){
        return followerUserService.processFollow(userId);
    }
}
