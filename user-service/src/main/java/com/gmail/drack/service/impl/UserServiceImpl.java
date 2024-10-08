package com.gmail.drack.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gmail.drack.repository.UserRepository;
import com.gmail.drack.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public <T> Page<T> searchUsersByUsername(
            String text,
            Pageable pageable,
            Class<T> type) {
        return userRepository.searchUsersByUsername(text, pageable, type);
    }

}
