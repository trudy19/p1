package com.example.hinking.services;
import com.example.hinking.dtos.GroupDTO;
import com.example.hinking.mappers.GroupMapper;
import com.example.hinking.models.Group;
import com.example.hinking.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(GroupMapper::toDTO)
                .collect(Collectors.toList());
    }

    public GroupDTO getGroupById(Long id) {
        Group group = groupRepository.findById(id).orElse(null);
        return GroupMapper.toDTO(group);
    }

    public GroupDTO createGroup(GroupDTO  groupDTO) {
        Group group = GroupMapper.toEntity(groupDTO);
        Group savedGroup = groupRepository.save(group);
        return GroupMapper.toDTO(savedGroup);    }

    public GroupDTO updateGroup(Long id, GroupDTO groupDetails) {
        Group group = groupRepository.findById(id).orElse(null);
        if (group != null) {
            group.setGroupName(groupDetails.getGroupName());
            Group updatedGroup = groupRepository.save(group);

            return GroupMapper.toDTO(updatedGroup);
        }
        return null;
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
