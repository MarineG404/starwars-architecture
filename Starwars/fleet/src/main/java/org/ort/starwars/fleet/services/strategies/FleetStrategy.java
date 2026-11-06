package org.ort.starwars.fleet.services.strategies;

import java.util.List;

import org.ort.starwars.fleet.domain.models.Mission;
import org.ort.starwars.fleet.domain.models.entities.Starship;

public interface FleetStrategy {
    boolean match(Mission mission);

    List<Starship> assign(List<Starship> all, Mission mission);
}
