package com.kbtu.notificationservice.repository;

import com.kbtu.notificationservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

        Optional<Notification> findBySourceEventId(String sourceEventId);

        List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);
}
