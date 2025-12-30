package com.kbtu.notificationservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(
        name = "notifications",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_notification_source_event",
                        columnNames = "source_event_id"
                )
        }
)
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status;

    @Column(columnDefinition = "TEXT")
    private String payload;

    @Column(name = "source_event_id", nullable = false, updatable = false)
    private String sourceEventId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "read_at")
    private Instant readAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.status = NotificationStatus.NEW;
    }

    protected Notification() {}

    public Notification(
            Long userId,
            NotificationType type,
            String payload,
            String sourceEventId
    ) {
        this.userId = userId;
        this.type = type;
        this.payload = payload;
        this.sourceEventId = sourceEventId;
    }

    public void markAsRead() {
        this.status = NotificationStatus.READ;
        this.readAt = Instant.now();
    }
}
