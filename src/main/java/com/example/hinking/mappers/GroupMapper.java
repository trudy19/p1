package com.example.hinking.mappers;

import com.example.hinking.dtos.GroupDTO;
import com.example.hinking.models.Group;

public class GroupMapper {

    public static GroupDTO toDTO(Group group) {
        if (group == null) {
            return null;
        }

        GroupDTO dto = new GroupDTO();
        dto.setGroupID(group.getGroupID());
        dto.setGroupName(group.getGroupName());

        return dto;
    }

    public static Group toEntity(GroupDTO dto) {
        if (dto == null) {
            return null;
        }

        Group group = new Group();
        group.setGroupID(dto.getGroupID());
        group.setGroupName(dto.getGroupName());

        return group;
    }
}
