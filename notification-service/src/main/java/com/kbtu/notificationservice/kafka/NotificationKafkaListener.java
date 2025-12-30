package com.kbtu.notificationservice.kafka;

import com.kbtu.notificationservice.kafka.dto.NotificationEvent;
import com.kbtu.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationKafkaListener {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "${kafka.topics.notification}",
            groupId = "${kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleNotificationEvent(NotificationEvent event) {
        log.info(
                "Received notification event: eventId={}, userId={}, type={}",
                event.getEventId(),
                event.getUserId(),
                event.getType()
        );

        notificationService.createFromEvent(
                event.getEventId(),
                event.getUserId(),
                event.getType(),
                event.getPayload()
        );
    }
}
