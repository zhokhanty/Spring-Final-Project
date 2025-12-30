package com.kbtu.notificationservice.service;

import com.kbtu.notificationservice.entity.Notification;
import com.kbtu.notificationservice.entity.NotificationType;
import com.kbtu.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    /**
     * Создание уведомления из события (Kafka)
     * ИДЕМПОТЕНТНЫЙ метод
     */
    @Transactional
    public void createFromEvent(
            String sourceEventId,
            Long userId,
            NotificationType type,
            String payload
    ) {
        // 1. Проверка на дубликат
        boolean alreadyExists =
                notificationRepository.findBySourceEventId(sourceEventId).isPresent();

        if (alreadyExists) {
            // событие уже обработано → ничего не делаем
            return;
        }

        // 2. Создание уведомления
        Notification notification = new Notification(
                userId,
                type,
                payload,
                sourceEventId
        );

        // 3. Сохранение
        notificationRepository.save(notification);
    }

    /**
     * Получение всех уведомлений пользователя
     */
    @Transactional(readOnly = true)
    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    /**
     * Отметить уведомление как прочитанное
     */
    @Transactional
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Notification not found: " + notificationId
                ));

        notification.markAsRead();
    }
}
