package com.example.traveling.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DestinationCreateModel {

    @NotBlank
    private String cityName;
    @NotBlank
    private String region;
    private String description;
    @Min(value = 1)
    @Max(value = 5)
    private int popularity;

}
