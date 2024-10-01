package com.example.hinking.controllers;


import com.example.hinking.dtos.ChatRoomDTO;
import com.example.hinking.models.ChatRoom;
import com.example.hinking.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping
    public List<ChatRoomDTO> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @GetMapping("/{id}")
    public ChatRoomDTO getChatRoomById(@PathVariable Long id) {
        return chatRoomService.getChatRoomById(id);
    }

    @PostMapping
    public ChatRoomDTO createChatRoom(@RequestBody ChatRoomDTO chatRoom) {
        return chatRoomService.createChatRoom(chatRoom);
    }

    @PutMapping("/{id}")
    public ChatRoomDTO updateChatRoom(@PathVariable Long id, @RequestBody ChatRoomDTO chatRoom) {
        return chatRoomService.updateChatRoom(id, chatRoom);
    }

    @DeleteMapping("/{id}")
    public void deleteChatRoom(@PathVariable Long id) {
        chatRoomService.deleteChatRoom(id);
    }
}

