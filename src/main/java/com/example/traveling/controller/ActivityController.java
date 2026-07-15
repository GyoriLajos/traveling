package com.example.traveling.controller;

import com.example.traveling.entity.Activity;
import com.example.traveling.service.ActivityService;
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
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        log.info("Request new Activity create");
        return ResponseEntity.status(201).body(activityService.save(activity));
    }

    @PutMapping("/{id}")
     public ResponseEntity<Activity> updateActivityById(@PathVariable Long id,@RequestBody Activity updatedActivity) {
        log.info("Request exist Activity update");
        return ResponseEntity.ok().body(activityService.updateById(id,updatedActivity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        log.info("GET request Activity by Id");
        return ResponseEntity.ok(activityService.getEntityById(id));
    }

    @GetMapping
    public ResponseEntity<List<Activity>> findAllActivities() {
        log.info("GET request of Activities list");
        return ResponseEntity.ok(activityService.findAllEntities());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Delete request of Activity by Id");
        activityService.deleteEntityById(id);
        return ResponseEntity.status(204).build();
    }
}