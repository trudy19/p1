package com.example.hinking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class HikeDTO {

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;

    @Size(max = 50)
    private String difficulty;

    @NotNull
    private Instant dateTime;

    @NotBlank
    @Size(max = 100)
    private String startPoint;

    @NotBlank
    @Size(max = 100)
    private String endPoint;

    @NotNull
    private Float distance;

    @NotNull
    private Float duration;

    @NotBlank
    @Size(max = 50)
    private String visibility;

    @NotNull
    private Long groupId;

    @NotNull
    private Long userId;

    @NotNull
    private Long categoryId;
}