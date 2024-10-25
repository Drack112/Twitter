package com.gmail.drack.service.impl;

import org.springframework.stereotype.Service;

import com.gmail.drack.commons.dto.response.user.UserResponse;
import com.gmail.drack.commons.mapper.BasicMapper;
import com.gmail.drack.repository.UserRepository;
import com.gmail.drack.repository.projection.UserProjection;
import com.gmail.drack.service.UserClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserClientServiceImpl implements UserClientService {
    
    private final UserRepository userRepository;
    private final BasicMapper basicMapper;

    @Override
    public UserResponse getUserResponseById(Long userId) {
        UserProjection user = userRepository.getUserById(userId, UserProjection.class).get();
        return basicMapper.convertToResponse(user, UserResponse.class);
    }

}
