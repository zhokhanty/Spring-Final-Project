package com.kbtu.notificationservice.service;

import com.kbtu.notificationservice.entity.Notification;
import com.kbtu.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Notification create(String type, String message) {
        Notification notification = new Notification();
        notification.setType(type);
        notification.setMessage(message);
        return notificationRepository.save(notification);
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }
}