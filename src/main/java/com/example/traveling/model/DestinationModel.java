package com.example.traveling.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DestinationModel {

    private Long id;
    private String cityName;
    private String region;
    private String description;
    private int popularity;
}
