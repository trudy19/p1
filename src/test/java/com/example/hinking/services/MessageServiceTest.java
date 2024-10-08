package com.example.hinking.services;

import com.example.hinking.dtos.MessageDTO;
import com.example.hinking.exceptions.ResourceNotFoundException;
import com.example.hinking.mappers.MessageMapper;
import com.example.hinking.models.Message;
import com.example.hinking.repositories.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    private Message message;
    private MessageDTO messageDTO;

    @BeforeEach
    public void setUp() {
        message = new Message();
        message.setMessageID(1L);
        message.setContent("Test Message");
        message.setDate(Instant.now());
        message.setMessageType("INFO");
        message.setStatus("SENT");
        messageDTO = MessageMapper.toDTO(message);
    }

    @Test
    public void testGetAllMessages() {
        when(messageRepository.findAll()).thenReturn(Arrays.asList(message));
        List<MessageDTO> messages = messageService.getAllMessages();
        assertEquals(1, messages.size());
        assertEquals("Test Message", messages.get(0).getContent());
        assertNotNull(messages.get(0).getDate()); // Vérifie que la date n'est pas nulle
        verify(messageRepository, times(1)).findAll();
    }

    @Test
    public void testGetMessageById() {
        when(messageRepository.findById(1L)).thenReturn(Optional.of(message));
        MessageDTO result = messageService.getMessageById(1L);
        assertNotNull(result);
        assertEquals("Test Message", result.getContent());
        assertNotNull(result.getDate()); // Vérifie que la date n'est pas nulle
        verify(messageRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetMessageByIdNotFound() {
        when(messageRepository.findById(2L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> messageService.getMessageById(2L));
        assertEquals("message not found with id: 2", exception.getMessage());
        verify(messageRepository, times(1)).findById(2L);
    }

    @Test
    public void testCreateMessage() {
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        MessageDTO createdMessage = messageService.createMessage(messageDTO);
        assertNotNull(createdMessage);
        assertEquals("Test Message", createdMessage.getContent());
        assertNotNull(createdMessage.getDate()); // Vérifie que la date n'est pas nulle
        verify(messageRepository, times(1)).save(any(Message.class));
    }

    @Test
    public void testUpdateMessage() {
        // Arrange
        MessageDTO updatedMessageDetails = new MessageDTO();
        updatedMessageDetails.setContent("Updated Message");
        updatedMessageDetails.setDate(Instant.now().plusSeconds(3600)); // Nouvelle date
        updatedMessageDetails.setMessageType("WARNING");
        updatedMessageDetails.setStatus("DELIVERED");
        when(messageRepository.findById(1L)).thenReturn(Optional.of(message));
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        MessageDTO updatedMessage = messageService.updateMessage(1L, updatedMessageDetails);
        assertNotNull(updatedMessage);
        assertEquals("Updated Message", updatedMessage.getContent());
        assertNotNull(updatedMessage.getDate()); // Vérifie que la date n'est pas nulle
        verify(messageRepository, times(1)).findById(1L);
        verify(messageRepository, times(1)).save(any(Message.class));
    }

    @Test
    public void testUpdateMessageNotFound() {

        MessageDTO updatedMessageDetails = new MessageDTO();
        updatedMessageDetails.setContent("Updated Message");
        when(messageRepository.findById(2L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> messageService.getMessageById(2L));
        assertEquals("message not found with id: 2", exception.getMessage());
        verify(messageRepository, times(1)).findById(2L);
        verify(messageRepository, times(0)).save(any(Message.class));
    }

    @Test
    public void testDeleteMessage() {
        messageService.deleteMessage(1L);
        verify(messageRepository, times(1)).deleteById(1L);
    }
}
