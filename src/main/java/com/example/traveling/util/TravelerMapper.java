package com.example.traveling.util;

import com.example.traveling.entity.Traveler;
import com.example.traveling.model.TravelerCreateModel;
import com.example.traveling.model.TravelerModel;

import static com.example.traveling.util.Mapper.map;

public class TravelerMapper {

    private TravelerMapper() {
    }

    public static Traveler mapCreateTravelerModelToTravelerEntity(TravelerCreateModel model) {
        return map(model, currentModel ->
                Traveler.builder()
                        .firstName(currentModel.getFirstName())
                        .lastName(currentModel.getLastName())
                        .email(currentModel.getEmail())
                        .phoneNumber(currentModel.getPhoneNumber())
                        .categoryPreference(currentModel.getCategoryPreference())
                        .budget(currentModel.getBudget())
                        .build());
    }

    public static TravelerModel mapTravelerEntityToTravelerModel(Traveler entity) {
        return map(entity, currentEntity ->
                TravelerModel.builder()
                        .id(currentEntity.getId())
                        .firstName(currentEntity.getFirstName())
                        .lastName(currentEntity.getLastName())
                        .email(currentEntity.getEmail())
                        .phoneNumber(currentEntity.getPhoneNumber())
                        .categoryPreference(currentEntity.getCategoryPreference())
                        .budget(currentEntity.getBudget())
                        .activities(map(currentEntity.getActivities(), ActivityMapper::mapActivityEntityToActivityModel))
                        .build());
    }
}