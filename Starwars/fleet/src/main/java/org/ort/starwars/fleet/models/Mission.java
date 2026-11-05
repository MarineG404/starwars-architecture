package org.ort.starwars.fleet.models;

import org.ort.starwars.fleet.models.enums.MissionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mission {
    private MissionType type;
    private boolean urgent;
    private int passengers;
    private int outwardDistance;   // HX ou HA+AB
    private int returnDistance;    // BH
}