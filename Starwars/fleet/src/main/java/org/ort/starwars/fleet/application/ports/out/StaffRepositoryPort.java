package org.ort.starwars.fleet.application.ports.out;

import org.ort.starwars.fleet.domain.models.entities.Staff;

public interface StaffRepositoryPort {
    Iterable<Staff> findAll();
}
