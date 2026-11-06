package org.ort.starwars.fleet.infrastructure.adapters.repositories;

import org.ort.starwars.fleet.domain.models.entities.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {

}
