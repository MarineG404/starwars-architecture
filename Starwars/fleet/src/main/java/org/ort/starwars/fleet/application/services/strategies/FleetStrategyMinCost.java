package org.ort.starwars.fleet.application.services.strategies;

import java.util.Comparator;
import java.util.List;

import org.ort.starwars.fleet.domain.models.Mission;
import org.ort.starwars.fleet.domain.models.entities.Starship;
import org.ort.starwars.fleet.domain.models.enums.MissionType;
import org.ort.starwars.fleet.application.services.strategies.helpers.FleetHelper;

public class FleetStrategyMinCost implements FleetStrategy {

    @Override
    public boolean match(Mission mission) {
        return mission.getType() == MissionType.TRANSPORT && !mission.isUrgent();
    }

    @Override
    public List<Starship> assign(List<Starship> all, Mission mission) {
        List<Starship> pool = FleetHelper.available(all);
        // Coût en carburant (taille²) par passager transporté, le plus économique d'abord
        pool.sort(Comparator.comparingDouble((Starship s) -> s.getCost() / FleetHelper.capacity(s)));
        return FleetHelper.fill(pool, mission.getPassengers());
    }
}
