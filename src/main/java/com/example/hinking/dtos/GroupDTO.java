package com.example.hinking.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GroupDTO {



    @NotBlank
    private String groupName;
}
