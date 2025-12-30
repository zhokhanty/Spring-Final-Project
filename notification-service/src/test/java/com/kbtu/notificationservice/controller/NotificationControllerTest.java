package com.kbtu.notificationservice.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.kbtu.notificationservice.entity.Notification;
import com.kbtu.notificationservice.entity.NotificationType;
import com.kbtu.notificationservice.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @Test
    void shouldReturnUserNotifications() throws Exception {
        Notification notification = mock(Notification.class);
        when(notification.getId()).thenReturn(1L);
        when(notification.getType()).thenReturn(NotificationType.BOOK_BORROWED);

        when(notificationService.getUserNotifications(1L))
                .thenReturn(List.of(notification));

        mockMvc.perform(get("/api/notifications")
                        .param("userId", "1"))
                .andExpect(status().isOk());
    }
}
