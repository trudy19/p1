package com.example.hinking.services;

import com.example.hinking.dtos.HikeDTO;
import com.example.hinking.exceptions.ResourceNotFoundException;
import com.example.hinking.mappers.HikeMapper;
import com.example.hinking.models.Category;
import com.example.hinking.models.Group;
import com.example.hinking.models.Hike;
import com.example.hinking.models.User;
import com.example.hinking.repositories.CategoryRepository;
import com.example.hinking.repositories.GroupRepository;
import com.example.hinking.repositories.HikeRepository;
import com.example.hinking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class HikeService {

    @Autowired
    private HikeRepository hikeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;


    public List<HikeDTO> getAllHikes() {
        return hikeRepository.findAll().stream()
                .map(HikeMapper::toDTO) // Convertir chaque Hike en HikeDTO
                .collect(Collectors.toList());
    }

    public HikeDTO getHikeById(Long id) {
        Hike hike = hikeRepository.findById(id).orElse(null);
        if (hike == null) {
            throw new ResourceNotFoundException("hike", id);
        }
        return HikeMapper.toDTO(hike);
    }

    public HikeDTO createHike(HikeDTO hikeDTO) {
        Hike hike = HikeMapper.toEntity(hikeDTO);
        Category category=categoryRepository.findById(hikeDTO.getCategoryId()).orElse(null);
        if(category==null){
            throw new ResourceNotFoundException("category", hikeDTO.getCategoryId());
        }
        Group group=groupRepository.findById(hikeDTO.getGroupId()).orElse(null);
        if(group==null){
            throw new ResourceNotFoundException("group", hikeDTO.getGroupId());
        }
        User userCreator=userRepository.findById(hikeDTO.getUserId()).orElse(null);
        if(userCreator==null){
            throw new ResourceNotFoundException("user", hikeDTO.getUserId());
        }
        hike.setCategory(category);
        hike.setGroup(group);
        hike.setCreator(userCreator);
        Hike savedHike = hikeRepository.save(hike);
        return HikeMapper.toDTO(savedHike);
    }

    public HikeDTO updateHike(Long id, HikeDTO hikeDetails) {
        Hike hike = hikeRepository.findById(id).orElse(null);
        Category category=categoryRepository.findById(hikeDetails.getCategoryId()).orElse(null);
        if(category==null){
            throw new ResourceNotFoundException("category", hikeDetails.getCategoryId());
        }
        if (hike == null) {
            throw new ResourceNotFoundException("hike", id);
        }
        if (hikeDetails.getName() != null) {
            hike.setName(hikeDetails.getName());
        }
        if (hikeDetails.getDescription() != null) {
            hike.setDescription(hikeDetails.getDescription());
        }
        if (hikeDetails.getDifficulty() != null) {
            hike.setDifficulty(hikeDetails.getDifficulty());
        }
        if (hikeDetails.getDateTime() != null) {
            hike.setDateTime(hikeDetails.getDateTime());
        }
        if (hikeDetails.getStartPoint() != null) {
            hike.setStartPoint(hikeDetails.getStartPoint());
        }
        if (hikeDetails.getEndPoint() != null) {
            hike.setEndPoint(hikeDetails.getEndPoint());
        }
        if (hikeDetails.getDistance() != null) {
            hike.setDistance(hikeDetails.getDistance());
        }
        if (hikeDetails.getDuration() != null) {
            hike.setDuration(hikeDetails.getDuration());
        }
        if (hikeDetails.getVisibility() != null) {
            hike.setVisibility(hikeDetails.getVisibility());
        }
        if (hikeDetails.getCategoryId() != null) {
            hike.setCategory(category);
        }
        hike = hikeRepository.save(hike);
        return HikeMapper.toDTO(hike);

    }

    public void deleteHike(Long id) {
        hikeRepository.deleteById(id);
    }
}
