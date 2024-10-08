package com.example.hinking.mappers;

import com.example.hinking.dtos.ChatRoomDTO;
import com.example.hinking.models.ChatRoom;

public class ChatRoomMapper {


    public static ChatRoomDTO toDTO(ChatRoom chatRoom) {
        ChatRoomDTO dto = new ChatRoomDTO();
        dto.setName(chatRoom.getName());
        return dto;
    }

    public static ChatRoom toEntity(ChatRoomDTO dto) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(dto.getName());
        return chatRoom;
    }
}
