package com.gmail.drack.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    <T> Page<T> searchUsersByUsername(String username, Pageable pageable, Class<T> type);
}
