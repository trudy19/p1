package com.example.hinking.services;

import com.example.hinking.dtos.NotificationDTO;
import com.example.hinking.mappers.NotificationMapper;
import com.example.hinking.mappers.UserMapper;
import com.example.hinking.models.Notification;
import com.example.hinking.models.User;
import com.example.hinking.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<NotificationDTO> getAllNotifications() {

        return notificationRepository.findAll().stream().map(NotificationMapper::toDTO).collect(Collectors.toList());
    }

    public NotificationDTO getNotificationById(Long id) {
        return notificationRepository.findById(id).map(NotificationMapper::toDTO).orElse(null);
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO) {


        Notification notification=NotificationMapper.toEntity(notificationDTO);
        notification=notificationRepository.save(notification);
        return NotificationMapper.toDTO(notification);
    }

    public NotificationDTO updateNotification(Long id, NotificationDTO notificationDetails) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification != null) {
            notification.setContent(notificationDetails.getContent());
            notification.setDate(notificationDetails.getDate());
            notification.setType(notificationDetails.getType());
            notification=notificationRepository.save(notification);
            return NotificationMapper.toDTO(notification);
        }
        return null;
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
