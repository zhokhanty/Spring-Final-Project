package com.kbtu.notificationservice.kafka;

import com.kbtu.api.contracts.event.BookBorrowedEvent;
import com.kbtu.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationKafkaListener {

    private final NotificationService notificationService;

    @KafkaListener(topics = "book-borrowed-topic", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void handleBookBorrowed(BookBorrowedEvent event) {

        log.info("Получено событие о выдаче книги: Book ID={}, User={}",
                event.getBookId(), event.getUsername());

        String message = String.format("Книга ID#%d была выдана пользователю '%s'.",
                event.getBookId(), event.getUsername());

        notificationService.create(
                "BOOK_BORROWED",
                message
        );
        log.info("Уведомление успешно сохранено в БД.");
    }
}