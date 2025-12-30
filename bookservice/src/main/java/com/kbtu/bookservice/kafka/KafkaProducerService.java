//package com.kbtu.bookservice.kafka;
//
//import com.kbtu.api.contracts.event.BookBorrowedEvent;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j; // <-- Добавлена аннотация Slf4j
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.CompletableFuture;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j // <-- Добавлено для логирования
//public class KafkaProducerService {
//
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//    private final String TOPIC = "book-borrowed-topic";
//
//    public void sendBookBorrowedEvent(BookBorrowedEvent event) {
//        log.info("Attempting to send BookBorrowedEvent to topic {} with event: {}", TOPIC, event);
//
//        // Используем CompletableFuture для асинхронной обработки результата
//        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC, String.valueOf(event.getBookId()), event);
//
//        future.whenComplete((result, ex) -> {
//            if (ex == null) {
//                // Успешная отправка
//                log.info("Successfully sent message [key={}, event={}] to topic {} at offset {}",
//                        result.getProducerRecord().key(), event, TOPIC,
//                        result.getRecordMetadata().offset());
//            } else {
//                // Сбой отправки (ВКЛЮЧАЯ СБОИ СЕРИАЛИЗАЦИИ)
//                log.error("Failed to send message [event={}] to topic {}", event, TOPIC, ex);
//            }
//        });
//
//        // ВНИМАНИЕ: .exceptionally(ex -> null) предотвращает необработанный сбой
//        future.exceptionally(ex -> {
//            log.error("Exception occurred during message send initialization", ex);
//            return null;
//        });
//    }
//}