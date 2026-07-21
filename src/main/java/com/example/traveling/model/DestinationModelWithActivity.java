package com.example.traveling.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DestinationModelWithActivity {

    private Long id;
    private String cityName;
    private String region;
    private String description;
    private int popularity;
    private List<ActivityModel> activities;
}
