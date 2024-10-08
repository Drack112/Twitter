package com.gmail.drack.mapper;

import org.springframework.stereotype.Component;

import com.gmail.drack.commons.event.UpdateUserEvent;
import com.gmail.drack.commons.mapper.BasicMapper;
import com.gmail.drack.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProducerMapper {
    private final BasicMapper basicMapper;

    public UpdateUserEvent toUpdateUserEvent(User user) {
        return basicMapper.convertToResponse(user, UpdateUserEvent.class);
    }

}
