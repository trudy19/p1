package com.example.hinking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDTO {



    @NotBlank(message = "name DTO is required")
    @Size(min = 3, max = 50)
    private String name;
}
