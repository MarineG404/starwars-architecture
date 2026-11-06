package org.ort.starwars.fleet.domain.models.enums;

public enum StarshipType {
    DESTROYER,
    DREADNOUGHT,
    STATION,
    FIGHTER,
    CRAFT,
    CARGO,
    OTHER;

    public static StarshipType of(String swapiClass) {
        if (swapiClass == null) {
            return OTHER;
        }
        String c = swapiClass.toLowerCase();
        if (c.contains("dreadnought")) return DREADNOUGHT;
        if (c.contains("destroyer")) return DESTROYER;
        if (c.contains("station")) return STATION;
        if (c.contains("fighter")) return FIGHTER;
        if (c.contains("craft")) return CRAFT;
        if (c.contains("cargo") || c.contains("freighter")) return CARGO;
        return OTHER;
    }
}
