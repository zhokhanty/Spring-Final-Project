package com.kbtu.userservice.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {

    private UUID eventId;
    private UUID userId;
    private Instant timestamp;

    // Дополнительные поля, если нужны для других сервисов
    private String eventType = "USER_CREATED";

    public UserCreatedEvent(UUID eventId, UUID userId, Instant now) {
        this.eventId = eventId;
        this.userId = userId;
        this.timestamp = now;
    }
}
