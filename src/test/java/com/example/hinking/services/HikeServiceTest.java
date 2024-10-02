package com.example.hinking.services;

import com.example.hinking.dtos.HikeDTO;
import com.example.hinking.mappers.HikeMapper;
import com.example.hinking.models.Hike;
import com.example.hinking.repositories.HikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HikeServiceTest {

    @Mock
    private HikeRepository hikeRepository;

    @InjectMocks
    private HikeService hikeService;

    private Hike hike;
    private HikeDTO hikeDTO;

    @BeforeEach
    public void setUp() {
        // Créer un objet Hike avec des valeurs de test
        hike = new Hike();
        hike.setHikeID(1L);
        hike.setName("Mountain Trail");
        hike.setDescription("A beautiful mountain trail.");
        hike.setDifficulty("Moderate");
        hike.setDateTime(Instant.now());
        hike.setStartPoint("Base Camp");
        hike.setEndPoint("Mountain Peak");
        hike.setDistance(5.0f);
        hike.setDuration(2.5f);
        hike.setVisibility("High");

        hikeDTO = HikeMapper.toDTO(hike); // Mapper vers DTO
    }

    @Test
    public void testGetAllHikes() {
        // Arrange
        when(hikeRepository.findAll()).thenReturn(Arrays.asList(hike));

        // Act
        List<HikeDTO> hikes = hikeService.getAllHikes();
        assertEquals(1, hikes.size());
        assertEquals("Mountain Trail", hikes.get(0).getName());
        assertNotNull(hikes.get(0).getDateTime()); // Vérifie que la date n'est pas nulle
        verify(hikeRepository, times(1)).findAll();
    }

    @Test
    public void testGetHikeById() {
        when(hikeRepository.findById(1L)).thenReturn(Optional.of(hike));
        HikeDTO result = hikeService.getHikeById(1L);
        assertNotNull(result);
        assertEquals("Mountain Trail", result.getName());
        assertNotNull(result.getDateTime()); // Vérifie que la date n'est pas nulle
        verify(hikeRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetHikeByIdNotFound() {
        when(hikeRepository.findById(2L)).thenReturn(Optional.empty());
        HikeDTO result = hikeService.getHikeById(2L);
        assertNull(result);
        verify(hikeRepository, times(1)).findById(2L);
    }

    @Test
    public void testCreateHike() {
        when(hikeRepository.save(any(Hike.class))).thenReturn(hike);
        HikeDTO createdHike = hikeService.createHike(hikeDTO);
        assertNotNull(createdHike);
        assertEquals("Mountain Trail", createdHike.getName());
        assertNotNull(createdHike.getDateTime()); // Vérifie que la date n'est pas nulle
        verify(hikeRepository, times(1)).save(any(Hike.class));
    }

    @Test
    public void testUpdateHike() {
        // Arrange
        HikeDTO updatedHikeDetails = new HikeDTO();
        updatedHikeDetails.setName("Updated Mountain Trail");
        updatedHikeDetails.setDescription("An updated beautiful mountain trail.");
        updatedHikeDetails.setDifficulty("Easy");
        updatedHikeDetails.setDateTime(Instant.now().plusSeconds(3600)); // Nouvelle date
        updatedHikeDetails.setStartPoint("New Base Camp");
        updatedHikeDetails.setEndPoint("New Mountain Peak");
        updatedHikeDetails.setDistance(6.0f);
        updatedHikeDetails.setDuration(3.0f);
        updatedHikeDetails.setVisibility("Medium");
        when(hikeRepository.findById(1L)).thenReturn(Optional.of(hike));
        when(hikeRepository.save(any(Hike.class))).thenReturn(hike);
        HikeDTO updatedHike = hikeService.updateHike(1L, updatedHikeDetails);
        assertNotNull(updatedHike);
        assertEquals("Updated Mountain Trail", updatedHike.getName());
        assertNotNull(updatedHike.getDateTime()); // Vérifie que la date n'est pas nulle
        verify(hikeRepository, times(1)).findById(1L);
        verify(hikeRepository, times(1)).save(any(Hike.class));
    }

    @Test
    public void testUpdateHikeNotFound() {
        HikeDTO updatedHikeDetails = new HikeDTO();
        updatedHikeDetails.setName("Updated Mountain Trail");
        when(hikeRepository.findById(2L)).thenReturn(Optional.empty());

        HikeDTO updatedHike = hikeService.updateHike(2L, updatedHikeDetails);
        assertNull(updatedHike);
        verify(hikeRepository, times(1)).findById(2L);
        verify(hikeRepository, times(0)).save(any(Hike.class));
    }

    @Test
    public void testDeleteHike() {
        hikeService.deleteHike(1L);
        verify(hikeRepository, times(1)).deleteById(1L);
    }
}
