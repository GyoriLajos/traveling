package com.example.traveling.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ActivityCreateModel {

    @NotBlank
    private String name;
    @NotNull
    private LocalDate date;
    @NotNull
    @PositiveOrZero
    private Double cost;
    @NotBlank
    private String location;
    @NotNull
    private Long destinationId;
}
