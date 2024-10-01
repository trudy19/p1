package com.example.hinking.controllers;

import com.example.hinking.dtos.NotificationDTO;
import com.example.hinking.models.Notification;
import com.example.hinking.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/notifications")


public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<NotificationDTO> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public NotificationDTO getNotificationById(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }

    @PostMapping
    public NotificationDTO createNotification(@RequestBody NotificationDTO notification) {
        return notificationService.createNotification(notification);
    }

    @PutMapping("/{id}")
    public NotificationDTO updateNotification(@PathVariable Long id, @RequestBody NotificationDTO notification) {
        return notificationService.updateNotification(id, notification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }
}
