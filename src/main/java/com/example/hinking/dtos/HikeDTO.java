package com.example.hinking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class HikeDTO {


    private Long hikeID;

    @NotBlank
    private String name;

    private String description;

    private String difficulty;

    @NotNull
    private Instant dateTime;

    @NotBlank
    private String startPoint;

    @NotBlank
    private String endPoint;

    @NotNull
    private Float distance;

    @NotNull
    private Float duration;

    @NotBlank
    private String visibility;

    private Long groupId;
}
