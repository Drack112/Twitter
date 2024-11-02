package com.gmail.drack.commons.event;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailEvent {
    private String toEmail;
    private String subject;
    private String template;
    private Map<String, Object> attributes;
}
