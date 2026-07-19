package com.example.traveling.util;

import com.example.traveling.entity.Activity;
import com.example.traveling.model.ActivityCreateModel;
import com.example.traveling.model.ActivityModel;

import static com.example.traveling.util.Mapper.map;

public class ActivityMapper {

    private ActivityMapper() {

    }

    public static Activity mapCreateActivityModelToActivityEntity(ActivityCreateModel model) {
        return map(model, currentModel ->
                Activity.builder()
                        .name(currentModel.getName())
                        .date(currentModel.getDate())
                        .cost(currentModel.getCost())
                        .location(currentModel.getLocation())
                        .build());
    }

    public static ActivityModel mapActivityEntityToActivityModel(Activity entity) {
        return map(entity, currentEntity ->
                ActivityModel.builder()
                        .id(currentEntity.getId())
                        .name(currentEntity.getName())
                        .date(currentEntity.getDate())
                        .cost(currentEntity.getCost())
                        .location(currentEntity.getLocation())
                        .build());
    }
}
