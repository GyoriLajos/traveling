package com.example.traveling.service;

import com.example.traveling.entity.Activity;
import com.example.traveling.entity.Destination;
import com.example.traveling.repository.ActivityRepository;
import com.example.traveling.repository.DestinationRepository;
import com.example.traveling.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class ActivityService extends BaseServiceImpl<Activity, Long, ActivityRepository> {

    private final DestinationRepository destinationRepository;

    public ActivityService(ActivityRepository repository, DestinationRepository destinationRepository) {
        super(repository);
        this.destinationRepository = destinationRepository;
    }

    @Transactional
    public Activity saveWithDestination(Activity activity, Long destinationId) {
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new NoSuchElementException("Destination not found with id: " + destinationId));
        activity.setDestination(destination);
        return repository.save(activity);
    }

    @Override
    public void updateMapper(Activity updatedEntity, Activity update) {
        updatedEntity.setName(update.getName());
        updatedEntity.setDate(update.getDate());
        updatedEntity.setCost(update.getCost());
        updatedEntity.setLocation(update.getLocation());
        if (update.getDestination() != null) {
            updatedEntity.setDestination(update.getDestination());
        }
    }
}