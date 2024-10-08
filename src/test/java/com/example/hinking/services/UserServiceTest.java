package com.example.hinking.services;


import com.example.hinking.dtos.CategoryDTO;
import com.example.hinking.dtos.UserDTO;
import com.example.hinking.exceptions.ResourceNotFoundException;
import com.example.hinking.mappers.UserMapper;
import com.example.hinking.models.Category;
import com.example.hinking.models.User;
import com.example.hinking.repositories.CategoryRepository;
import com.example.hinking.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


// TODO: Ajouter un test pour changement qu'un seul champ


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;
    private User user;
    private UserDTO userDTO;


    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUserID(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setProfilePic("profilePic.jpg");

        userDTO = UserMapper.toDTO(user);
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        // Act
        List<UserDTO> users = userService.getAllUsers();

        // Assert
        assertEquals(1, users.size());
        assertEquals("John Doe", users.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        UserDTO result = userService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateUser() {
        // Arrange
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserDTO createdUser = userService.createUser(userDTO);

        // Assert
        assertNotNull(createdUser);
        assertEquals("John Doe", createdUser.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser() {
        User updatedUserDetails = new User();
        updatedUserDetails.setName("Updated Name");
        updatedUserDetails.setEmail("updated.email@example.com");
        updatedUserDetails.setPassword("newpassword");
        updatedUserDetails.setProfilePic("updatedPic.jpg");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updatedUserDetails);
        UserDTO updatedUser = userService.updateUser(1L, UserMapper.toDTO(updatedUserDetails));
        assertNotNull(updatedUser);
        assertEquals("Updated Name", updatedUser.getName());
        assertEquals("updated.email@example.com", updatedUser.getEmail());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUserNotFound() {

        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.updateUser(2L, userDTO);
        });
        System.out.println(exception.getMessage());
        assertEquals("User not found with id: 2", exception.getMessage());

        verify(userRepository, times(1)).findById(2L);
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

}
