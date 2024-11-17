package com.gmail.drack.broker.utils;

import org.apache.kafka.clients.producer.ProducerRecord;

import com.gmail.drack.commons.constants.PathConstants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProducerUtil {
    public static <V> ProducerRecord<String, V> authHeaderWrapper(String topic, V event, Long authUserId) {
        ProducerRecord<String, V> producerRecord = new ProducerRecord<>(topic, event);
        producerRecord.headers().add(PathConstants.AUTH_USER_ID_HEADER, authUserId.toString().getBytes());
        return producerRecord;
    }
}
