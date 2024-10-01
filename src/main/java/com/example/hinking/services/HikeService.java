package com.example.hinking.services;
import com.example.hinking.dtos.HikeDTO;
import com.example.hinking.mappers.HikeMapper;
import com.example.hinking.models.Hike;
import com.example.hinking.repositories.HikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class HikeService {

    @Autowired
    private HikeRepository hikeRepository;

    public List<HikeDTO> getAllHikes() {
        return hikeRepository.findAll().stream()
                .map(HikeMapper::toDTO) // Convertir chaque Hike en HikeDTO
                .collect(Collectors.toList());
    }

    public HikeDTO getHikeById(Long id) {
        Hike hike = hikeRepository.findById(id).orElse(null);

        return  HikeMapper.toDTO(hike);
    }

    public HikeDTO createHike(HikeDTO hikeDTO) {
        Hike hike = HikeMapper.toEntity(hikeDTO);
        Hike savedHike = hikeRepository.save(hike);
        return HikeMapper.toDTO(savedHike);
    }

    public HikeDTO updateHike(Long id, HikeDTO hikeDetails) {
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
            hike=hikeRepository.save(hike);
            return HikeMapper.toDTO(hike);
        }
        return null;
    }

    public void deleteHike(Long id) {
        hikeRepository.deleteById(id);
    }
}
