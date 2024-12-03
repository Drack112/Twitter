package com.gmail.drack.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gmail.drack.repository.projection.BaseUserProjection;
import com.gmail.drack.repository.projection.FollowerUserProjection;
import com.gmail.drack.repository.projection.UserProjection;

public interface FollowerUserService {
    Page<UserProjection> getFollowers(Long userId, Pageable pageable);
    Page<UserProjection> getFollowing(Long userId, Pageable pageable);
    Page<FollowerUserProjection> getFollowersRequest(Pageable pageable);
    Boolean processFollow(Long userId);
    List<BaseUserProjection> overallFollowers(Long userId);
}

