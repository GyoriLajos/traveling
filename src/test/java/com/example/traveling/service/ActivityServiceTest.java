package com.example.traveling.service;

import com.example.traveling.entity.Activity;
import com.example.traveling.entity.Destination;
import com.example.traveling.repository.ActivityRepository;
import com.example.traveling.repository.DestinationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {
    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private ActivityService activityService;

    @Test
    void saveWithDestination_ShouldSaveActivity_WhenDestinationExists() {
        // ARRANGE
        Long destinationId = 1L;
        Destination destination = new Destination();
        destination.setId(destinationId);

        Activity activity = new Activity();
        activity.setName("Túrázás");

        when(destinationRepository.findById(destinationId)).thenReturn(Optional.of(destination));

        when(activityRepository.save(any(Activity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        Activity result = activityService.saveWithDestination(activity, destinationId);

        // ASSERT
        assertNotNull(result);
        assertEquals(destination, result.getDestination(), "Az Activity-hez be kellett állítani a Destination-t");
        verify(destinationRepository, times(1)).findById(destinationId);
        verify(activityRepository, times(1)).save(activity);
    }

    @Test
    void saveWithDestination_ShouldThrowException_WhenDestinationNotFound() {
        // ARRANGE
        Long destinationId = 99L;
        Activity activity = new Activity();


        when(destinationRepository.findById(destinationId)).thenReturn(Optional.empty());

        // ACT & ASSERT

        assertThrows(NoSuchElementException.class, () -> {
            activityService.saveWithDestination(activity, destinationId);
        });


        verify(activityRepository, never()).save(any());
    }


    @Test
    void updatemapper_ShouldUpdateAllFields() {
        // ARRANGE
        Destination newDestination = new Destination();

        Activity existing = new Activity();
        existing.setName("Régi Név");
        existing.setCost(100.0);
        existing.setLocation("Budapest");
        existing.setDate(LocalDate.of(2026,11,2));
        existing.setDestination(newDestination);

        Activity update = new Activity();
        update.setName("Új Név");
        update.setCost(200.0);
        update.setLocation("Budapest");
        update.setDate(LocalDate.of(2026,11,2));
        update.setDestination(newDestination);

        // ACT
        activityService.updatemapper(existing, update);

        // ASSERT
        assertEquals("Új Név", existing.getName());
        assertEquals(200.0, existing.getCost());
        assertEquals("Budapest", existing.getLocation());
        assertEquals(newDestination, existing.getDestination());
    }
}
