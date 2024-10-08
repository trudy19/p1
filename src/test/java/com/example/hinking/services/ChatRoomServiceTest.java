package com.example.hinking.services;


import com.example.hinking.dtos.ChatRoomDTO;
import com.example.hinking.exceptions.ResourceNotFoundException;
import com.example.hinking.mappers.ChatRoomMapper;
import com.example.hinking.models.ChatRoom;
import com.example.hinking.repositories.ChatRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChatRoomServiceTest {


    @Mock
    private
    ChatRoomRepository chatRoomRepository;

    @InjectMocks
    private ChatRoomService chatRoomService;

    private ChatRoom chatRoom;
    private ChatRoomDTO chatRoomDTO;

    @BeforeEach
    public void setUp() {
        chatRoom = new ChatRoom();
        chatRoom.setChatRoomID(1L);
        chatRoom.setName("General Chat");
        chatRoomDTO = ChatRoomMapper.toDTO(chatRoom);
    }

    @Test
    public void testGetAllChatRooms() {
        when(chatRoomRepository.findAll()).thenReturn(Arrays.asList(chatRoom));
        List<ChatRoomDTO> chatRooms = chatRoomService.getAllChatRooms();
        assertEquals(1, chatRooms.size());
        assertEquals("General Chat", chatRooms.get(0).getName());
        verify(chatRoomRepository, times(1)).findAll();
    }

    @Test
    public void testGetChatRoomById() {
        when(chatRoomRepository.findById(1L)).thenReturn(Optional.of(chatRoom));
        ChatRoomDTO result = chatRoomService.getChatRoomById(1L);
        assertNotNull(result);
        assertEquals("General Chat", result.getName());
        verify(chatRoomRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetChatRoomByIdNotFound() {
        when(chatRoomRepository.findById(2L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> chatRoomService.getChatRoomById(2L));
        assertEquals("chatRoom not found with id: 2", exception.getMessage());

        verify(chatRoomRepository, times(1)).findById(2L);
    }

    @Test
    public void testCreateChatRoom() {
        when(chatRoomRepository.save(any(ChatRoom.class))).thenReturn(chatRoom);
        ChatRoomDTO createdChatRoom = chatRoomService.createChatRoom(chatRoomDTO);
        assertNotNull(createdChatRoom);
        assertEquals("General Chat", createdChatRoom.getName());
        verify(chatRoomRepository, times(1)).save(any(ChatRoom.class));
    }

    @Test
    public void testUpdateChatRoom() {
        ChatRoomDTO updatedChatRoomDetails = new ChatRoomDTO();
        updatedChatRoomDetails.setName("Updated Chat Room");
        when(chatRoomRepository.findById(1L)).thenReturn(Optional.of(chatRoom));
        when(chatRoomRepository.save(any(ChatRoom.class))).thenReturn(chatRoom);
        ChatRoomDTO updatedChatRoom = chatRoomService.updateChatRoom(1L, updatedChatRoomDetails);
        assertNotNull(updatedChatRoom);
        assertEquals("Updated Chat Room", updatedChatRoom.getName());
        verify(chatRoomRepository, times(1)).findById(1L);
        verify(chatRoomRepository, times(1)).save(any(ChatRoom.class));
    }

    @Test
    public void testUpdateChatRoomNotFound() {
        // Arrange
        ChatRoomDTO updatedChatRoomDetails = new ChatRoomDTO();
        updatedChatRoomDetails.setName("Updated Chat Room");
        when(chatRoomRepository.findById(2L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> chatRoomService.updateChatRoom(2L, updatedChatRoomDetails));
        assertEquals("chatRoom not found with id: 2", exception.getMessage());
        
        verify(chatRoomRepository, times(1)).findById(2L);
        verify(chatRoomRepository, times(0)).save(any(ChatRoom.class));
    }

    @Test
    public void testDeleteChatRoom() {
        doNothing().when(chatRoomRepository).deleteById(1L);

        chatRoomService.deleteChatRoom(1L);
        verify(chatRoomRepository, times(1)).deleteById(1L);
    }
}
