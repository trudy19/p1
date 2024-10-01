package com.example.hinking.mappers;

import com.example.hinking.dtos.NotificationDTO;
import com.example.hinking.models.Notification;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationMapper {

    public static NotificationDTO toDTO(Notification notification) {
        if (notification == null) {
            return null;
        }
        NotificationDTO dto = new NotificationDTO();
        dto.setNotificationID(notification.getNotificationID());
        dto.setContent(notification.getContent());
        dto.setDate(notification.getDate());
        dto.setType(notification.getType());
        return dto;
    }

    public static Notification toEntity(NotificationDTO dto) {
        if (dto == null) {
            return null;
        }
        Notification notification = new Notification();
        notification.setContent(dto.getContent());
        notification.setDate(dto.getDate());
        notification.setType(dto.getType());
        return notification;
    }


}
