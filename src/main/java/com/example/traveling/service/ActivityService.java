package com.example.traveling.service;

import com.example.traveling.entity.Activity;
import com.example.traveling.repository.ActivityRepository;
import com.example.traveling.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActivityService extends BaseServiceImpl<Activity, Long, ActivityRepository> {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository repository) {
        super(repository);
        this.activityRepository = repository;
    }

    @Override
    public void updatemapper(Activity updatedEntity, Activity update) {
        updatedEntity.setName(update.getName());
        updatedEntity.setDate(update.getDate());
        updatedEntity.setCost(update.getCost());
        updatedEntity.setLocation(update.getLocation());
    }
}
