package com.kbtu.notificationservice.controller;

import com.kbtu.notificationservice.dto.NotificationResponseDto;
import com.kbtu.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Получить все уведомления пользователя
     */
    @GetMapping
    public ResponseEntity<List<NotificationResponseDto>> getUserNotifications(
            @RequestParam Long userId
    ) {
        List<NotificationResponseDto> response =
                notificationService.getUserNotifications(userId)
                        .stream()
                        .map(NotificationResponseDto::new)
                        .toList();

        return ResponseEntity.ok(response);
    }

    /**
     * Отметить уведомление как прочитанное
     */
    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<Void> markAsRead(
            @PathVariable Long notificationId
    ) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.noContent().build();
    }
}
