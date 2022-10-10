package sin2cos2.extremeSportRestAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sin2cos2.extremeSportRestAPI.entities.Trip;

public interface SportLocationRepository extends JpaRepository<Trip, Long> {
}