package com.example.hinking.controllers;



import com.example.hinking.dtos.GroupDTO;
import com.example.hinking.models.Group;
import com.example.hinking.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/groups")



public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public List<GroupDTO> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public GroupDTO getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @PostMapping
    public GroupDTO createGroup(@RequestBody GroupDTO group) {
        return groupService.createGroup(group);
    }

    @PutMapping("/{id}")
    public GroupDTO updateGroup(@PathVariable Long id, @RequestBody Group group) {
        return groupService.updateGroup(id, group);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
}
