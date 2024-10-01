package com.example.hinking.controllers;


import com.example.hinking.dtos.MessageDTO;
import com.example.hinking.models.Message;
import com.example.hinking.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<MessageDTO> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public MessageDTO getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }

    @PostMapping
    public MessageDTO createMessage(@RequestBody MessageDTO message) {
        return messageService.createMessage(message);
    }

    @PutMapping("/{id}")
    public MessageDTO updateMessage(@PathVariable Long id, @RequestBody MessageDTO message) {
        return messageService.updateMessage(id, message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
}
