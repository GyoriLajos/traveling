package com.example.traveling.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ActivityModelWithTraveler {

    private Long id;
    private String name;
    private LocalDate date;
    private Double cost;
    private String location;
    private List<TravelerModel> travelers;
}
