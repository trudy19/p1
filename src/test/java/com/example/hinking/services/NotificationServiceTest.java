package com.example.hinking.services;

import com.example.hinking.dtos.NotificationDTO;
import com.example.hinking.mappers.NotificationMapper;
import com.example.hinking.models.Notification;
import com.example.hinking.repositories.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    private Notification notification;
    private NotificationDTO notificationDTO;

    @BeforeEach
    public void setUp() {
        notification = new Notification();
        notification.setNotificationID(1L);
        notification.setContent("Test Notification");
        notification.setDate(Instant.now());
        notification.setType("INFO");

        notificationDTO = NotificationMapper.toDTO(notification);
    }

    @Test
    public void testGetAllNotifications() {
        // Arrange
        when(notificationRepository.findAll()).thenReturn(Arrays.asList(notification));

        // Act
        List<NotificationDTO> notifications = notificationService.getAllNotifications();

        // Assert
        assertEquals(1, notifications.size());
        assertEquals("Test Notification", notifications.get(0).getContent());
        verify(notificationRepository, times(1)).findAll();
    }

    @Test
    public void testGetNotificationById() {
        // Arrange
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));

        // Act
        NotificationDTO result = notificationService.getNotificationById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Test Notification", result.getContent());
        verify(notificationRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetNotificationByIdNotFound() {
        // Arrange
        when(notificationRepository.findById(2L)).thenReturn(Optional.empty());

        // Act
        NotificationDTO result = notificationService.getNotificationById(2L);

        // Assert
        assertNull(result);
        verify(notificationRepository, times(1)).findById(2L);
    }

    @Test
    public void testCreateNotification() {
        // Arrange
        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        // Act
        NotificationDTO createdNotification = notificationService.createNotification(notificationDTO);

        // Assert
        assertNotNull(createdNotification);
        assertEquals("Test Notification", createdNotification.getContent());
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    public void testUpdateNotification() {
        // Arrange
        NotificationDTO updatedNotificationDetails = new NotificationDTO();
        updatedNotificationDetails.setContent("Updated Notification");
        updatedNotificationDetails.setDate(Instant.now());
        updatedNotificationDetails.setType("WARNING");

        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));
        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        // Act
        NotificationDTO updatedNotification = notificationService.updateNotification(1L, updatedNotificationDetails);

        // Assert
        assertNotNull(updatedNotification);
        assertEquals("Updated Notification", updatedNotification.getContent());
        verify(notificationRepository, times(1)).findById(1L);
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    public void testUpdateNotificationNotFound() {
        // Arrange
        NotificationDTO updatedNotificationDetails = new NotificationDTO();
        updatedNotificationDetails.setContent("Updated Notification");

        when(notificationRepository.findById(2L)).thenReturn(Optional.empty());

        // Act
        NotificationDTO updatedNotification = notificationService.updateNotification(2L, updatedNotificationDetails);

        // Assert
        assertNull(updatedNotification);
        verify(notificationRepository, times(1)).findById(2L);
        verify(notificationRepository, times(0)).save(any(Notification.class));
    }

    @Test
    public void testDeleteNotification() {
        // Act
        notificationService.deleteNotification(1L);

        // Assert
        verify(notificationRepository, times(1)).deleteById(1L);
    }
}
