package com.example.traveling.service;

import com.example.traveling.entity.Destination;
import com.example.traveling.repository.DestinationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DestinationServiceTest {

    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private DestinationService destinationService;

    @Test
    @DisplayName("updateMapper - sikeresen frissíti a Destination mezőit")
    void updateMapper_ShouldUpdateAllFields() {
        Destination existing = Destination.builder()
                .id(1L)
                .cityName("párizs")
                .region("francia")
                .description("régi leírás")
                .popularity(3)
                .build();

        Destination update = Destination.builder()
                .cityName("Párizs")
                .region("Franciaország")
                .description("új leírás")
                .popularity(5)
                .build();

        destinationService.updateMapper(existing, update);
        assertEquals("Párizs", existing.getCityName());
        assertEquals("Franciaország", existing.getRegion());
        assertEquals("új leírás", existing.getDescription());
        assertEquals(5, existing.getPopularity());
    }

    @Test
    @DisplayName("getEntityById - kivételt dob, ha a Destination nem található")
    void getEntityById_ShouldThrowException_WhenNotFound() {
        Long nonExistentId = 99L;
        when(destinationRepository.findById(nonExistentId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> destinationService.getEntityById(nonExistentId));
        verify(destinationRepository, times(1)).findById(nonExistentId);
    }
}