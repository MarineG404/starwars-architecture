package org.ort.starwars.fleet.application.services;

import java.util.Collections;
import java.util.List;

import org.ort.starwars.fleet.domain.models.Mission;
import org.ort.starwars.fleet.domain.models.entities.Starship;
import org.ort.starwars.fleet.infrastructure.adapters.repositories.StarshipRepository;
import org.ort.starwars.fleet.domain.services.strategies.FleetStrategy;
import org.ort.starwars.fleet.domain.services.strategies.FleetStrategyDefaut;
import org.ort.starwars.fleet.domain.services.strategies.FleetStrategyMaxSpeed;
import org.ort.starwars.fleet.domain.services.strategies.FleetStrategyMinCost;
import org.ort.starwars.fleet.domain.services.strategies.FleetStrategyMinEntropy;
import org.ort.starwars.fleet.utils.Iterables;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FleetService {

    private final FleetStrategy[] strategies = {
            new FleetStrategyMinEntropy(),
            new FleetStrategyMinCost(),
            new FleetStrategyMaxSpeed(),
            new FleetStrategyDefaut()
    };

    private final StarshipRepository repository;

    /**
     * Réserve une flotte de vaisseaux avec une stratégie adaptée à la mission
     */
    public List<Starship> forMission(Mission mission) {
        List<Starship> starships = Iterables.toList(repository.findAll());
        for (FleetStrategy strategy : strategies) {
            if (strategy.match(mission)) {
                return strategy.assign(starships, mission);
            }
        }

        // Aucune stratégie ne s'applique !
        return Collections.emptyList();
    }
}
