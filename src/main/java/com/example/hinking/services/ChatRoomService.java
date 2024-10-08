package com.example.hinking.services;

import com.example.hinking.dtos.ChatRoomDTO;
import com.example.hinking.exceptions.ResourceNotFoundException;
import com.example.hinking.mappers.ChatRoomMapper;
import com.example.hinking.models.ChatRoom;
import com.example.hinking.repositories.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public List<ChatRoomDTO> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        return chatRooms.stream()
                .map(ChatRoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ChatRoomDTO getChatRoomById(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElse(null);
        if(chatRoom==null) {
            throw new ResourceNotFoundException("chatRoom", id);
        }
        return ChatRoomMapper.toDTO(chatRoom) ;
    }

    public ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO) {
        ChatRoom chatRoom = ChatRoomMapper.toEntity(chatRoomDTO);
        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
        return ChatRoomMapper.toDTO(savedChatRoom);
    }

    public ChatRoomDTO updateChatRoom(Long id, ChatRoomDTO chatRoomDetails) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElse(null);
        if(chatRoom==null) {
            throw new ResourceNotFoundException("chatRoom", id);
        }
            chatRoom.setName(chatRoomDetails.getName());
            ChatRoom updatedChatRoom = chatRoomRepository.save(chatRoom);
            return ChatRoomMapper.toDTO(updatedChatRoom);
    }

    public void deleteChatRoom(Long id) {
        chatRoomRepository.deleteById(id);
    }
}
