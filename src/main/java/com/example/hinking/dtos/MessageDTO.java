package com.example.hinking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class MessageDTO {

    private Long messageID;
    @NotBlank
    private String content;

    @NotNull
    private Instant date;

    private String messageType;
    private String status;

    private Long chatRoomId;
    private Long senderId;
}
