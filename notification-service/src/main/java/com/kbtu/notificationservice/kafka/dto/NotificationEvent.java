package com.kbtu.notificationservice.kafka.dto;

import com.kbtu.notificationservice.entity.NotificationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationEvent {

    // уникальный id события (UUID из producer)
    private String eventId;

    // пользователь, которому адресовано уведомление
    private Long userId;

    // тип уведомления
    private NotificationType type;

    // данные события (JSON строка)
    private String payload;
}
