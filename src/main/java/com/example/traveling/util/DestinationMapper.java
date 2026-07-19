package com.example.traveling.util;

import com.example.traveling.entity.Destination;
import com.example.traveling.model.DestinationCreateModel;
import com.example.traveling.model.DestinationModel;

import static com.example.traveling.util.Mapper.map;

public class DestinationMapper {

    private DestinationMapper() {
    }

    public static Destination mapCreateDestinationModelToDestinationEntity(DestinationCreateModel model) {
        return map(model, currentModel ->
                Destination.builder()
                        .cityName(currentModel.getCityName())
                        .region(currentModel.getRegion())
                        .description(currentModel.getDescription())
                        .popularity(currentModel.getPopularity())
                        .build());
    }

    public static DestinationModel mapDestinationEntityToDestinationModel(Destination entity) {
        return map(entity, currentEntity ->
                DestinationModel.builder()
                        .id(currentEntity.getId())
                        .cityName(currentEntity.getCityName())
                        .region(currentEntity.getRegion())
                        .description(currentEntity.getDescription())
                        .popularity(currentEntity.getPopularity())
                        .activities(map(currentEntity.getActivities(), ActivityMapper::mapActivityEntityToActivityModel))
                        .build());
    }
}
