package com.gmail.drack.commons.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNotificationDTO {
    private Long id;
    private String username;
    private String avatar;
}
