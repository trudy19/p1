package com.example.hinking.controllers;


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
    public List<Hike> getAllHikes() {
        return hikeService.getAllHikes();
    }

    @GetMapping("/{id}")
    public Hike getHikeById(@PathVariable Long id) {
        return hikeService.getHikeById(id);
    }

    @PostMapping
    public Hike createHike(@RequestBody Hike hike) {
        return hikeService.createHike(hike);
    }

    @PutMapping("/{id}")
    public Hike updateHike(@PathVariable Long id, @RequestBody Hike hike) {
        return hikeService.updateHike(id, hike);
    }

    @DeleteMapping("/{id}")
    public void deleteHike(@PathVariable Long id) {
        hikeService.deleteHike(id);
    }
}



