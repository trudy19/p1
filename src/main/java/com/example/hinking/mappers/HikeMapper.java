package com.example.hinking.mappers;

import com.example.hinking.dtos.HikeDTO;
import com.example.hinking.models.Hike;

public class HikeMapper {
    public static HikeDTO toDTO(Hike hike) {
        if (hike == null) {
            return null;
        }

        HikeDTO dto = new HikeDTO();
        dto.setHikeID(hike.getHikeID());
        dto.setName(hike.getName());
        dto.setDescription(hike.getDescription());
        dto.setDifficulty(hike.getDifficulty());
        dto.setDateTime(hike.getDateTime());
        dto.setStartPoint(hike.getStartPoint());
        dto.setEndPoint(hike.getEndPoint());
        dto.setDistance(hike.getDistance());
        dto.setDuration(hike.getDuration());
        dto.setVisibility(hike.getVisibility());

        return dto;
    }


    public static Hike toEntity(HikeDTO dto) {
        if (dto == null) {
            return null;
        }

        Hike hike = new Hike();
        hike.setName(dto.getName());
        hike.setDescription(dto.getDescription());
        hike.setDifficulty(dto.getDifficulty());
        hike.setDateTime(dto.getDateTime());
        hike.setStartPoint(dto.getStartPoint());
        hike.setEndPoint(dto.getEndPoint());
        hike.setDistance(dto.getDistance());
        hike.setDuration(dto.getDuration());
        hike.setVisibility(dto.getVisibility());

        return hike;
    }
}
