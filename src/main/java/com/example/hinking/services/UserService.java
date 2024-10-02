package com.example.hinking.services;

import com.example.hinking.dtos.UserDTO;
import com.example.hinking.mappers.UserMapper;
import com.example.hinking.models.User;
import com.example.hinking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO) // Utilisez le mapper
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDTO).orElse(null);    }


    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    public UserDTO updateUser(Long id, UserDTO userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (userDetails.getName() != null) {
                user.setName(userDetails.getName());
            }

            if (userDetails.getEmail() != null) {
                user.setEmail(userDetails.getEmail());
            }

            if (userDetails.getPassword() != null) {
                user.setPassword(userDetails.getPassword());
            }

            if (userDetails.getProfilePic() != null) {
                user.setProfilePic(userDetails.getProfilePic());
            }
            user = userRepository.save(user);
            return UserMapper.toDTO(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
