package com.example.hinking.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long categoryID;

    @NotBlank
    private String name;
}
