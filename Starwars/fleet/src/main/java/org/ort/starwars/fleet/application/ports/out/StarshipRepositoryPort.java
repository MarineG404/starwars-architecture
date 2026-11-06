package org.ort.starwars.fleet.application.ports.out;

import org.ort.starwars.fleet.domain.models.entities.Starship;

public interface StarshipRepositoryPort {
    Iterable<Starship> findAll();
}
