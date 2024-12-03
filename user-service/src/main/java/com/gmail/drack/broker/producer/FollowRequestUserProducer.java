package com.gmail.drack.broker.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.gmail.drack.broker.utils.ProducerUtil;
import com.gmail.drack.commons.constants.KafkaTopicConstants;
import com.gmail.drack.commons.event.FollowRequestUserEvent;
import com.gmail.drack.mapper.ProducerMapper;
import com.gmail.drack.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FollowRequestUserProducer {
    private final KafkaTemplate<String, FollowRequestUserEvent> kafkaTemplate;
    private final ProducerMapper producerMapper;

    public void sendFollowRequestUserEvent(User user, Long authUserId, boolean hasUSerFollowRequest) {
        FollowRequestUserEvent event = producerMapper.toFollowRequestUserEvent(user, hasUSerFollowRequest);
        kafkaTemplate.send(ProducerUtil.authHeaderWrapper(KafkaTopicConstants.FOLLOW_REQUEST_USER_TOPIC, event, authUserId));
    }
}
