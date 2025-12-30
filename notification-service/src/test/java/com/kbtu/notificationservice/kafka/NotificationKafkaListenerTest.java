package com.kbtu.notificationservice.kafka;

import com.kbtu.notificationservice.entity.NotificationType;
import com.kbtu.notificationservice.kafka.dto.NotificationEvent;
import com.kbtu.notificationservice.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotificationKafkaListenerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationKafkaListener listener;

    @Test
    void shouldConsumeKafkaMessage() {
        NotificationEvent event = new NotificationEvent();
        event.setEventId("event-123");
        event.setUserId(1L);
        event.setType(NotificationType.BOOK_BORROWED);
        event.setPayload("{}");

        listener.handleNotificationEvent(event);

        verify(notificationService)
                .createFromEvent(
                        "event-123",
                        1L,
                        NotificationType.BOOK_BORROWED,
                        "{}"
                );
    }
}
