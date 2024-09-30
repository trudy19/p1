package com.example.hinking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class NotificationDTO {

    private Long notificationID;

    @NotBlank
    private String content;

    @NotNull
    private Instant date;

    @NotBlank
    private String type;

    private Long recipientId;
}
