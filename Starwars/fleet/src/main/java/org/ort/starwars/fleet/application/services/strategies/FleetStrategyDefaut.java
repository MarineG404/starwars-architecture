package org.ort.starwars.fleet.application.services.strategies;

import org.ort.starwars.fleet.domain.models.Mission;

public class FleetStrategyDefaut extends FleetStrategyMinEntropy {

    @Override
    public boolean match(Mission mission) {
        return true;
    }
}
