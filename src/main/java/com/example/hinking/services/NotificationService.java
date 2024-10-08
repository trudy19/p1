package com.example.hinking.services;

import com.example.hinking.dtos.NotificationDTO;
import com.example.hinking.exceptions.ResourceNotFoundException;
import com.example.hinking.mappers.NotificationMapper;
import com.example.hinking.mappers.UserMapper;
import com.example.hinking.models.Notification;
import com.example.hinking.models.User;
import com.example.hinking.repositories.NotificationRepository;
import com.example.hinking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<NotificationDTO> getAllNotifications() {

        return notificationRepository.findAll().stream().map(NotificationMapper::toDTO).collect(Collectors.toList());
    }

    public NotificationDTO getNotificationById(Long id) {
        NotificationDTO notificationDTO = notificationRepository.findById(id).map(NotificationMapper::toDTO).orElse(null);
        if (notificationDTO == null) {
            throw new ResourceNotFoundException("Notification", id);
        }
        return notificationDTO;
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO) {

        User userSender = userRepository.findById(notificationDTO.getSenderID()).orElse(null);
        if (userSender == null) {
            throw new ResourceNotFoundException("user", notificationDTO.getSenderID());
        }
        User userReceiver = userRepository.findById(notificationDTO.getReceiverID()).orElse(null);
        if (userReceiver == null) {
            throw new ResourceNotFoundException("user", notificationDTO.getReceiverID());
        }
        Notification notification = NotificationMapper.toEntity(notificationDTO);
        notification.setRecipient(userReceiver);
        notification.setSender(userSender);
        notification = notificationRepository.save(notification);
        return NotificationMapper.toDTO(notification);

    }

    public NotificationDTO updateNotification(Long id, NotificationDTO notificationDetails) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification == null) {
            throw new ResourceNotFoundException("Notification", id);
        }

        if (notificationDetails.getContent() != null) {
            notification.setContent(notificationDetails.getContent());
        }
        if (notificationDetails.getDate() != null) {
            notification.setDate(notificationDetails.getDate());
        }
        if (notificationDetails.getType() != null) {
            notification.setType(notificationDetails.getType());
        }
        notification = notificationRepository.save(notification);
        return NotificationMapper.toDTO(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
