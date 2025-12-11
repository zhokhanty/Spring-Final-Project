package com.kbtu.bookservice.kafka;

import com.kbtu.bookservice.event.BookBorrowedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendBookBorrowedEvent(BookBorrowedEvent event) {
        kafkaTemplate.send("book-borrowed-topic", event);
    }
}
