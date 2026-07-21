package com.example.traveling.model;


import lombok.*;

import java.time.LocalDate;

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
}
