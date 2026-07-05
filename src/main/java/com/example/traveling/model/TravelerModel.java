package com.example.traveling.model;

import com.example.traveling.entity.Activity;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TravelerModel {

    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String phoneNumber;
    private String categoryPreference;
    private Double budget;
    private List<DestinationModel> destinations;
    private List<ActivityModel> activities;

}
