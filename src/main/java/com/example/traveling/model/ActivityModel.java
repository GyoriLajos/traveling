package com.example.traveling.model;

import com.example.traveling.entity.Traveler;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ActivityModel {

    private Long id;
    private String name;
    private LocalDate date;
    private Double cost;
    private String location;
    private List<TravelerModel> travelers;
    private List<DestinationModel> destinations;
}
