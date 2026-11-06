package org.ort.starwars.fleet.services.strategies.helpers;

import java.util.ArrayList;
import java.util.List;

import org.ort.starwars.fleet.domain.models.entities.Starship;
import org.ort.starwars.fleet.domain.models.enums.StarshipType;

public final class FleetHelper {

    private FleetHelper() {
    }

    /** Capacité de passagers (SWAPI renvoie -1 quand elle est inconnue). */
    public static int capacity(Starship ship) {
        return Math.max(0, ship.getPassengers());
    }

    /**
     * Vaisseaux utilisables : un exemplaire par unité disponible (count),
     * sans les stations (immobiles) ni les vaisseaux sans place.
     */
    public static List<Starship> available(List<Starship> all) {
        List<Starship> result = new ArrayList<>();
        for (Starship ship : all) {
            if (ship.getCategory() == StarshipType.STATION || capacity(ship) == 0) {
                continue;
            }
            for (int i = 0; i < ship.getCount(); i++) {
                result.add(ship);
            }
        }
        return result;
    }

    /** Prend les vaisseaux dans l'ordre donné jusqu'à couvrir les passagers. Liste vide si impossible. */
    public static List<Starship> fill(List<Starship> sorted, int passengers) {
        List<Starship> chosen = new ArrayList<>();
        int remaining = passengers;
        for (Starship ship : sorted) {
            if (remaining <= 0) {
                break;
            }
            chosen.add(ship);
            remaining -= capacity(ship);
        }
        return remaining > 0 ? List.of() : chosen;
    }
}
