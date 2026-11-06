package org.ort.starwars.fleet.services.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.ort.starwars.fleet.domain.models.Mission;
import org.ort.starwars.fleet.domain.models.entities.Starship;
import org.ort.starwars.fleet.domain.models.enums.MissionType;
import org.ort.starwars.fleet.services.strategies.helpers.FleetHelper;

public class FleetStrategyMinEntropy implements FleetStrategy {

    @Override
    public boolean match(Mission mission) {
        return mission.getType() == MissionType.TRANSPORT && mission.isUrgent();
    }

    @Override
    public List<Starship> assign(List<Starship> all, Mission mission) {
        List<Starship> pool = FleetHelper.available(all);
        pool.sort(Comparator.comparingInt((Starship s) -> FleetHelper.capacity(s)).reversed());

        List<Starship> chosen = new ArrayList<>();
        int remaining = mission.getPassengers();

        while (remaining > 0) {
            if (pool.isEmpty()) {
                return List.of(); // pas assez de places
            }
            // Le pool est trié par capacité décroissante :
            // le dernier qui suffit seul est le plus petit possible (meilleur remplissage)
            Starship fit = null;
            for (Starship s : pool) {
                if (FleetHelper.capacity(s) >= remaining) {
                    fit = s;
                }
            }
            Starship next = fit != null ? fit : pool.get(0); // sinon, le plus grand
            pool.remove(next);
            chosen.add(next);
            remaining -= FleetHelper.capacity(next);
        }
        return chosen;
    }
}
