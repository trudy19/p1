package com.example.hinking.services;
import com.example.hinking.models.Hike;
import com.example.hinking.repositories.HikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HikeService {

    @Autowired
    private HikeRepository hikeRepository;

    public List<Hike> getAllHikes() {
        return hikeRepository.findAll();
    }

    public Hike getHikeById(Long id) {
        return hikeRepository.findById(id).orElse(null);
    }

    public Hike createHike(Hike hike) {
        return hikeRepository.save(hike);
    }

    public Hike updateHike(Long id, Hike hikeDetails) {
        Hike hike = hikeRepository.findById(id).orElse(null);
        if (hike != null) {
            hike.setName(hikeDetails.getName());
            hike.setDescription(hikeDetails.getDescription());
            hike.setDifficulty(hikeDetails.getDifficulty());
            hike.setDateTime(hikeDetails.getDateTime());
            hike.setStartPoint(hikeDetails.getStartPoint());
            hike.setEndPoint(hikeDetails.getEndPoint());
            hike.setDistance(hikeDetails.getDistance());
            hike.setDuration(hikeDetails.getDuration());
            hike.setVisibility(hikeDetails.getVisibility());
            return hikeRepository.save(hike);
        }
        return null;
    }

    public void deleteHike(Long id) {
        hikeRepository.deleteById(id);
    }
}
