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
    @NotNull(message = "A mezőt kötelöző kitölteni")
    private LocalDate date;
    @NotNull(message = "A mezőt kötelező kitölteni")
    @PositiveOrZero(message = "Az érték nem negatív szám")
    private Double cost;
    @NotBlank(message = "A mezőt kötelező kitölteni")
    private String location;
    @NotNull(message = "A mezőt kötelező kitölteni")
    private Long destinationId;
}
