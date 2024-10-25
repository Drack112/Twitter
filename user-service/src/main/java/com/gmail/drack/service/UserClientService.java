package com.gmail.drack.service;

import com.gmail.drack.commons.dto.response.user.UserResponse;

public interface UserClientService {
    UserResponse getUserResponseById(Long userId);
}
