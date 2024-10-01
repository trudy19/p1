package com.example.hinking.controllers;


import com.example.hinking.dtos.HikeDTO;
import com.example.hinking.models.Hike;
import com.example.hinking.services.HikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/hikes")
public class HikeController {

    @Autowired
    private HikeService hikeService;

    @GetMapping
    public List<HikeDTO> getAllHikes() {
        return hikeService.getAllHikes();
    }

    @GetMapping("/{id}")
    public HikeDTO getHikeById(@PathVariable Long id) {
        return hikeService.getHikeById(id);
    }

    @PostMapping
    public HikeDTO createHike(@RequestBody HikeDTO hike) {
        return hikeService.createHike(hike);
    }

    @PutMapping("/{id}")
    public HikeDTO updateHike(@PathVariable Long id, @RequestBody HikeDTO hike) {
        return hikeService.updateHike(id, hike);
    }

    @DeleteMapping("/{id}")
    public void deleteHike(@PathVariable Long id) {
        hikeService.deleteHike(id);
    }



}



