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
}
