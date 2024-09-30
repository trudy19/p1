package com.example.hinking.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GroupDTO {

    private Long groupID;

    @NotBlank
    private String groupName;
}
