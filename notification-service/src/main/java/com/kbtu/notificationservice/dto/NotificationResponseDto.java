package com.kbtu.notificationservice.dto;

import com.kbtu.notificationservice.entity.Notification;
import com.kbtu.notificationservice.entity.NotificationStatus;
import com.kbtu.notificationservice.entity.NotificationType;
import lombok.Getter;

import java.time.Instant;

@Getter
public class NotificationResponseDto {

    private final Long id;
    private final NotificationType type;
    private final NotificationStatus status;
    private final String payload;
    private final Instant createdAt;
    private final Instant readAt;

    public NotificationResponseDto(Notification notification) {
        this.id = notification.getId();
        this.type = notification.getType();
        this.status = notification.getStatus();
        this.payload = notification.getPayload();
        this.createdAt = notification.getCreatedAt();
        this.readAt = notification.getReadAt();
    }
}
