package org.ort.starwars.fleet.gateways.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;



@Data  
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiPageDto {
    private int count;
    private String next;
    private String previous;
    private List<SwapiStarshipDto> results;
}