package com.example.hinking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class NotificationDTO {


    @NotBlank
    @Size(max = 500)
    private String content;

    @NotNull
    private Instant date;

    @NotBlank
    @Size(max = 50)
    private String type;

    private Long senderID;

    private Long receiverID;
}
