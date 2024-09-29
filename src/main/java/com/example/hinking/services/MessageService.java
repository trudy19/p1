package com.example.hinking.services;

import com.example.hinking.models.Message;
import com.example.hinking.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessage(Long id, Message messageDetails) {
        Message message = messageRepository.findById(id).orElse(null);
        if (message != null) {
            message.setContent(messageDetails.getContent());
            message.setDate(messageDetails.getDate());
            message.setMessageType(messageDetails.getMessageType());
            message.setStatus(messageDetails.getStatus());
            return messageRepository.save(message);
        }
        return null;
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
