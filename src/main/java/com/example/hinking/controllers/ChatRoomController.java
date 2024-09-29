package com.example.hinking.controllers;


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
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @GetMapping("/{id}")
    public ChatRoom getChatRoomById(@PathVariable Long id) {
        return chatRoomService.getChatRoomById(id);
    }

    @PostMapping
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
        return chatRoomService.createChatRoom(chatRoom);
    }

    @PutMapping("/{id}")
    public ChatRoom updateChatRoom(@PathVariable Long id, @RequestBody ChatRoom chatRoom) {
        return chatRoomService.updateChatRoom(id, chatRoom);
    }

    @DeleteMapping("/{id}")
    public void deleteChatRoom(@PathVariable Long id) {
        chatRoomService.deleteChatRoom(id);
    }
}

