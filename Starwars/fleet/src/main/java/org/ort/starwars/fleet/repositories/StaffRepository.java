package org.ort.starwars.fleet.repositories;

import org.ort.starwars.fleet.models.entities.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {

}