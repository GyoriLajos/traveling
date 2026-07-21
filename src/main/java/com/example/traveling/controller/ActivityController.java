package com.example.traveling.controller;

import com.example.traveling.entity.Activity;
import com.example.traveling.model.ActivityCreateModel;
import com.example.traveling.model.ActivityModel;
import com.example.traveling.model.ActivityModelWithTraveler;
import com.example.traveling.service.ActivityService;
import com.example.traveling.util.ActivityMapper;
import com.example.traveling.util.Mapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activities")
@Slf4j
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityModel> createActivity(@Valid @RequestBody ActivityCreateModel activityCreateModel) {
        log.info("Request new Activity create");
        Activity activity = ActivityMapper.mapCreateActivityModelToActivityEntity(activityCreateModel);
        Activity savedActivity = activityService.saveWithDestination(activity, activityCreateModel.getDestinationId());
        return ResponseEntity.status(201).body(ActivityMapper.mapActivityEntityToActivityModel(savedActivity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityModel> updateActivityById(@PathVariable Long id, @Valid @RequestBody ActivityCreateModel updatedActCreateMo) {
        log.info("Request exist Activity update");
        Activity activity = ActivityMapper.mapCreateActivityModelToActivityEntity(updatedActCreateMo);
        Activity savedActivity = activityService.updateById(id, activity);
        return ResponseEntity.ok(ActivityMapper.mapActivityEntityToActivityModel(savedActivity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityModel> getActivityById(@PathVariable Long id) {
        log.info("GET request Activity by Id");
        Activity activity = activityService.getEntityById(id);
        return ResponseEntity.ok(ActivityMapper.mapActivityEntityToActivityModel(activity));
    }

    @GetMapping("/{id}/travelers")
    public ResponseEntity<ActivityModelWithTraveler> getActivityByIdWithTraveler(@PathVariable Long id) {
        log.info("GET request Activity by Id with Travelers");
        Activity activity = activityService.getEntityById(id);
        return ResponseEntity.ok(ActivityMapper.mapActivityEntityToActivityModelWithTraveler(activity));
    }

    @GetMapping
    public ResponseEntity<List<ActivityModel>> findAllActivities() {
        log.info("GET request of Activities list");
        List<Activity> activities = activityService.findAllEntities();
        return ResponseEntity.ok(Mapper.map(activities, ActivityMapper::mapActivityEntityToActivityModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Delete request of Activity by Id");
        activityService.deleteEntityById(id);
        return ResponseEntity.status(204).build();
    }
}