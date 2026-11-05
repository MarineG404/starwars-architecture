package org.ort.starwars.fleet.services.strategies;

import org.ort.starwars.fleet.models.Mission;

public class FleetStrategyDefaut extends FleetStrategyMinEntropy {

    @Override
    public boolean match(Mission mission) {
        return true;
    }
}