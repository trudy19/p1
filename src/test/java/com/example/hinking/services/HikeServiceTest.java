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
    @Mock
    private UserRepository userRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private HikeService hikeService;

    private Hike hike;
    private HikeDTO hikeDTO;
    private Category category;
    private Group group;
    private User user;

    @BeforeEach
    public void setUp() {
        // Créer un objet Hike avec des valeurs de test
        group = new Group();
        group.setGroupID(1L);
        category = new Category();
        category.setCategoryID(1L);

        user = new User();
        user.setUserID(1L);

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
        hike.setCategory(category);
        hike.setGroup(group);
        hike.setCreator(user);
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
        assertThrows(ResourceNotFoundException.class, () -> hikeService.getHikeById(2L));
        verify(hikeRepository, times(1)).findById(2L);
    }

    @Test
    public void testCreateHike() {
        when(hikeRepository.save(any(Hike.class))).thenReturn(hike);

        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));


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
        updatedHikeDetails.setCategoryId(1L);
        when(hikeRepository.findById(1L)).thenReturn(Optional.of(hike));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

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
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> hikeService.updateHike(2L, updatedHikeDetails));
        assertEquals("hike not found with id: 2", exception.getMessage());
        verify(hikeRepository, times(1)).findById(2L);
        verify(hikeRepository, times(0)).save(any(Hike.class));
    }

    @Test
    public void testDeleteHike() {
        hikeService.deleteHike(1L);
        verify(hikeRepository, times(1)).deleteById(1L);
    }
}
