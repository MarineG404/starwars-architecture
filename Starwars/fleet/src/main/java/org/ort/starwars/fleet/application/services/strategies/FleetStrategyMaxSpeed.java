package org.ort.starwars.fleet.application.services.strategies;

import java.util.Comparator;
import java.util.List;

import org.ort.starwars.fleet.domain.models.Mission;
import org.ort.starwars.fleet.domain.models.entities.Starship;
import org.ort.starwars.fleet.domain.models.enums.MissionType;
import org.ort.starwars.fleet.application.services.strategies.helpers.FleetHelper;

public class FleetStrategyMaxSpeed implements FleetStrategy {

    @Override
    public boolean match(Mission mission) {
        return mission.getType() == MissionType.RESCUE;
    }

    @Override
    public List<Starship> assign(List<Starship> all, Mission mission) {
        List<Starship> pool = FleetHelper.available(all);
        // La flotte va à la vitesse du plus lent : on prend les plus rapides d'abord
        pool.sort(Comparator.comparingDouble((Starship s) -> s.getSpeed()).reversed());
        return FleetHelper.fill(pool, mission.getPassengers());
    }
}
