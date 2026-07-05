package com.example.traveling.service;


import com.example.traveling.entity.Activity;
import com.example.traveling.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService {

    private final ActivityRepository activityRepository;

    public Activity newActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity updateActivityById(Long id,Activity updatedActivity) {
        Optional<Activity> existingActivity = activityRepository.findById(id);
        if (existingActivity.isEmpty()) {
            String message = String.format("Activity with id %d not found",id);
            logAndThrowException(message,new NoSuchElementException(message));
        }
        Activity activityToUpdate = existingActivity.get();
        activityToUpdate.setName(updatedActivity.getName());
        activityToUpdate.setDate(updatedActivity.getDate());
        activityToUpdate.setCost(updatedActivity.getCost());
        activityToUpdate.setLocation(updatedActivity.getLocation());
        return activityRepository.save(activityToUpdate);
    }

    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElseThrow(()-> {
            String message = String.format("Activity with id %d not found",id);
            return logAndReturnException(new NoSuchElementException(message),message);
        });
    }

    public List<Activity> findAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        if (activities.isEmpty()) {
            String message = "Activity database is empty";
            logAndThrowException(message,new RuntimeException(message));
        }
        return activities;
    }

    public void deleteById(Long id) {
        if (!activityRepository.existsById(id)) {
            String message = String.format("Activity with id %d not found",id);
            logAndThrowException(message,new NoSuchElementException(message));
        }
        activityRepository.deleteById(id);
    }

    private void logAndThrowException(String message, RuntimeException exception) {
        log.info(message);
        throw exception;
    }

    private RuntimeException logAndReturnException(RuntimeException exception, String message) {
        log.info(message);
        return exception;
    }
}
