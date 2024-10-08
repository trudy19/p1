package com.example.hinking.mappers;

import com.example.hinking.dtos.MessageDTO;
import com.example.hinking.models.Message;

public class MessageMapper {


    // Convertir l'entité Message vers le DTO MessageDTO
    public static MessageDTO toDTO(Message message) {
        if (message == null) {
            return null;
        }

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent(message.getContent());
        messageDTO.setDate(message.getDate());
        messageDTO.setMessageType(message.getMessageType());
        messageDTO.setStatus(message.getStatus());

        return messageDTO;
    }

    // Convertir le DTO MessageDTO vers l'entité Message
    public static Message toEntity(MessageDTO messageDTO) {
        if (messageDTO == null) {
            return null;
        }

        Message message = new Message();
        message.setContent(messageDTO.getContent());
        message.setDate(messageDTO.getDate());
        message.setMessageType(messageDTO.getMessageType());
        message.setStatus(messageDTO.getStatus());

        return message;
    }
}
