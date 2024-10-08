package com.example.hinking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class MessageDTO {


    @NotBlank
    @Size(max = 500)
    private String content;

    @NotNull
    private Instant date;

    @Size(max = 50)
    private String messageType;

    @Size(max = 50)
    private String status;

    private Long chatRoomId;

    private Long senderId;
}
