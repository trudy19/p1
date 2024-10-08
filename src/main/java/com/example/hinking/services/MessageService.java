package com.example.hinking.services;

import com.example.hinking.dtos.MessageDTO;
import com.example.hinking.exceptions.ResourceNotFoundException;
import com.example.hinking.mappers.MessageMapper;
import com.example.hinking.models.Message;
import com.example.hinking.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<MessageDTO> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(MessageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MessageDTO getMessageById(Long id) {
        Message message = messageRepository.findById(id).orElse(null);
        if (message == null) {
            throw new ResourceNotFoundException("message", id);
        }
        return MessageMapper.toDTO(message);
    }

    public MessageDTO createMessage(MessageDTO messageDTO) {
        Message message = MessageMapper.toEntity(messageDTO); // Convertir le DTO en entité
        Message savedMessage = messageRepository.save(message);
        return MessageMapper.toDTO(savedMessage);
    }

    public MessageDTO updateMessage(Long id, MessageDTO messageDetails) {
        Message message = messageRepository.findById(id).orElse(null);
        if (message == null) {
            throw new ResourceNotFoundException("message", id);
        }
        message.setContent(messageDetails.getContent());
        message.setDate(messageDetails.getDate());
        message.setMessageType(messageDetails.getMessageType());
        message.setStatus(messageDetails.getStatus());
        Message updatedMessage = messageRepository.save(message);
        return MessageMapper.toDTO(updatedMessage);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
