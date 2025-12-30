package com.kbtu.notificationservice.service;

import com.kbtu.notificationservice.entity.Notification;
import com.kbtu.notificationservice.entity.NotificationType;
import com.kbtu.notificationservice.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void shouldCreateNotificationIfNotExists() {
        when(notificationRepository.findBySourceEventId("event-1"))
                .thenReturn(Optional.empty());

        notificationService.createFromEvent(
                "event-1",
                1L,
                NotificationType.BOOK_BORROWED,
                "{}"
        );

        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    void shouldNotCreateDuplicateNotification() {
        when(notificationRepository.findBySourceEventId("event-1"))
                .thenReturn(Optional.of(mock(Notification.class)));

        notificationService.createFromEvent(
                "event-1",
                1L,
                NotificationType.BOOK_BORROWED,
                "{}"
        );

        verify(notificationRepository, never()).save(any());
    }
}
