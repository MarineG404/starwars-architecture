package org.ort.starwars.fleet.gateways.mappers;

import org.ort.starwars.fleet.domain.models.entities.Starship;
import org.ort.starwars.fleet.domain.models.enums.StarshipType;
import org.ort.starwars.fleet.gateways.dtos.SwapiStarshipDto;
import org.ort.starwars.fleet.utils.Parsing;
import org.springframework.stereotype.Component;

@Component
public class SwapiStarshipMapper {

    public Starship toDomain(SwapiStarshipDto dto) {
        return toDomain(dto, null);
    }

    public Starship toDomain(SwapiStarshipDto dto, Long id) {
        return Starship.builder()
                .id(id)
                .name(dto.getName())
                .count(dto.getCount())
                .crew(parseRange(dto.getCrew()))
                .passengers(parseRange(dto.getPassengers()))
                .category(StarshipType.of(dto.getStarshipClass()))
                .length(Parsing.getInt(dto.getLength(), -1))
                .build();
    }

    // Routines de parsing
    private int parseRange(String value) {
        int[] range = Parsing.getRange(value);
        return range.length > 0 ? range[0] : 0;
    }
}
