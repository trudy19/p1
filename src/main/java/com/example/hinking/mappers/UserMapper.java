package com.example.hinking.mappers;

import com.example.hinking.dtos.UserDTO;
import com.example.hinking.models.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserID(user.getUserID());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setProfilePic(user.getProfilePic());
        return dto;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setProfilePic(userDTO.getProfilePic());
        return user;
    }
}
