package com.gmail.drack.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gmail.drack.commons.dto.HeaderResponse;
import com.gmail.drack.commons.dto.response.user.UserResponse;
import com.gmail.drack.commons.mapper.BasicMapper;
import com.gmail.drack.repository.projection.UserProjection;
import com.gmail.drack.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final BasicMapper basicMapper;
    private final UserService userService;

    public HeaderResponse<UserResponse> searchUsersByUsername(String username, Pageable pageable) {
        Page<UserProjection> users = userService
                .searchUsersByUsername(username, pageable, UserProjection.class);
        return basicMapper.getHeaderResponse(users, UserResponse.class);
    }

}
