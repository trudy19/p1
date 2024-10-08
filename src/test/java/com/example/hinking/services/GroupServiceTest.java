package com.example.hinking.services;


import com.example.hinking.dtos.GroupDTO;
import com.example.hinking.dtos.UserDTO;
import com.example.hinking.exceptions.ResourceNotFoundException;
import com.example.hinking.mappers.GroupMapper;
import com.example.hinking.models.Group;
import com.example.hinking.models.User;
import com.example.hinking.repositories.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;
    GroupDTO groupDTO;
    Group group;

    @BeforeEach
    public void setUp() {
        group = new Group();
        group.setGroupName("groupe 1");
        group.setGroupID(1L);
        groupDTO = GroupMapper.toDTO(group);

    }

    @Test
    public void testGetAllGroups() {
        when(groupRepository.findAll()).thenReturn(Arrays.asList(group));
        List<GroupDTO> groups = groupService.getAllGroups();
        assertEquals(1, groups.size());
        assertEquals("groupe 1", groups.get(0).getGroupName());
        verify(groupRepository, times(1)).findAll();

    }

    @Test
    public void testGetGroupById() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        GroupDTO result = groupService.getGroupById(1L);
        assertNotNull(result);
        assertEquals("groupe 1", result.getGroupName());
        verify(groupRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateGroup() {
        when(groupRepository.save(any(Group.class))).thenReturn(group);
        GroupDTO createdGroup = groupService.createGroup(groupDTO);
        assertNotNull(createdGroup);
        assertEquals("groupe 1", createdGroup.getGroupName());
        verify(groupRepository, times(1)).save(any(Group.class));
    }


    @Test
    public void testUpdateGroup() {
        Group updatedGroupDetails = new Group();
        updatedGroupDetails.setGroupName("Updated Name");
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        GroupDTO result =groupService.updateGroup(1L,GroupMapper.toDTO(updatedGroupDetails));

        assertNotNull(result);
        assertEquals("Updated Name", result.getGroupName());
        verify(groupRepository, times(1)).findById(1L);
    }
    @Test
    public void testDeleteUser() {
        groupService.deleteGroup(1L);
        verify(groupRepository, times(1)).deleteById(1L);
    }
    @Test
    public void testUpdateGroupNotFound() {

        when(groupRepository.findById(2L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> groupService.updateGroup(2L,groupDTO));
        assertEquals("group not found with id: 2", exception.getMessage());
        verify(groupRepository, times(1)).findById(2L);
        verify(groupRepository, times(0)).save(any(Group.class));
    }

}
