package org.ort.starwars.fleet.infrastructure.adapters.gateways.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiStarshipDto {
    private String name;
    private String length;
    private String crew;
    private String passengers;

    @JsonProperty("starship_class")
    private String starshipClass;

    // SWAPI ne fournit pas de quantité : 1 exemplaire par défaut
    private int count = 1;
}